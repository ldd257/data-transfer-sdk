package com.cngc.transfer.sdk.consumer.processor;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.aspect.DataProcessor;
import com.cngc.transfer.sdk.aspect.DataRun;
import com.cngc.transfer.sdk.common.TransferConfig;
import com.cngc.transfer.sdk.context.DataApplicationContextAware;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 消费者消费消息路由
 * .<br/>
 *
 * Copyright: Copyright (c) 2020  cngc
 *
 * @ClassName: RocketMQMessageListenerConcurrentlyProcessor
 * @Description:
 * @version: v1.0.0
 * @author: yuan
 * @date: 2018年2月28日 上午11:12:32
 * Modification History:
 * Date             Author          Version            Description
 *---------------------------------------------------------*
 * 2018年2月28日      yuan           v1.0.0               创建
 */
@Component
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {
	private static final Logger logger = LoggerFactory.getLogger(MQConsumeMsgListenerProcessor.class);

	@Autowired
	private DefaultMQProducer mqProducer;

	/**
	 *  默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息<br/>
	 *  不要抛异常，如果没有return CONSUME_SUCCESS ，consumer会重新消费该消息，直到return CONSUME_SUCCESS
	 */
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		logger.info("开始接受消息");
		if(CollectionUtils.isEmpty(msgs)){
			logger.info("接受到的消息为空，不处理，直接返回成功");
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		MessageExt messageExt = msgs.get(0);
		logger.info("接受到的消息总览为：{}",messageExt);
//		if(messageExt.getTopic().equals("DemoTopic")){
		String data = new String(messageExt.getBody());
		try {
			JSONArray jsonArray = JSONUtil.parseArray(data);
			invokeMethod(messageExt.getTags(), jsonArray);
		} catch (Exception e) {
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		// 如果没有return success ，consumer会重新消费该消息，直到return success
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

	/**
	 * 根据tags匹配方法并执行
	 * @param tags
	 * @param parameter 参数
	 */
	private void invokeMethod(String tags,JSONArray parameter) {
		Map<String, Object> map = DataApplicationContextAware.getApplicationContext();
		Class<? extends Object> clazz = null;
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			clazz = entry.getValue().getClass();//获取到实例对象的class信息
			// 获取 "类" 上的注解
			DataProcessor getAnnotation = clazz.getAnnotation(DataProcessor.class);
			logger.info("注解参数："+getAnnotation.value());
			if(tags.equals(getAnnotation.value())){
				Method[] method = clazz.getMethods();
				for(Method m:method){
					DataRun dataRun = m.getAnnotation(DataRun.class);
					send(parameter, entry, m, dataRun);
				}
			}
		}
	}

	private void send(JSONArray parameter, Map.Entry<String, Object> entry, Method m, DataRun dataRun) {
		if(null!=dataRun){
			try {
				Type[] parameterTypes = m.getGenericParameterTypes();
				Console.log(parameterTypes[0]);
				ParameterizedType t = (ParameterizedType)parameterTypes[0];
				Console.log(t.getClass());
				Console.log(t.getActualTypeArguments()[0]);// 可以得到参数化类型的参数实例
				Object obj = m.invoke(entry.getValue(), parameter.toList(t.getClass()));
				logger.error("处理结果：{}",obj);
				SendResult sendResult = sendToServer(obj);
				logger.info("发送处理结果："+sendResult + " 至Server端");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SneakyThrows
	private SendResult sendToServer(Object obj) {
		String processId = "1";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", processId);
		jsonObject.put("status", obj);
		Message sendMsg = new Message(TransferConfig.TRANSFER_PLATFORM_TOPIC, TransferConfig.TRANSFER_PLATFORM_TAGS, JSONUtil.toJsonStr(jsonObject).getBytes());
		//默认3秒超时
		mqProducer.setVipChannelEnabled(false);
		SendResult sendResult = mqProducer.send(sendMsg);
		return sendResult;
	}

}
