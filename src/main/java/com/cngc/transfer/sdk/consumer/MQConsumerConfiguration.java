package com.cngc.transfer.sdk.consumer;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.common.MyRequestUtils;
import com.cngc.transfer.sdk.common.TransferConfig;
import com.cngc.transfer.sdk.constants.RocketMQErrorEnum;
import com.cngc.transfer.sdk.consumer.processor.MQConsumeMsgListenerProcessor;
import com.cngc.transfer.sdk.exception.RocketMQException;
import com.cngc.transfer.sdk.producer.MQProducerConfigBean;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;


/**
 * 消费者Bean配置
 * .<br/>
 *
 * Copyright: Copyright (c) 2020  cngc
 *
 * @ClassName: MQConsumerConfiguration
 * @Description:
 * @version: v1.0.0
 * @author: yuan
 * @date: 2018年3月2日 下午11:48:32
 * Modification History:
 * Date             Author          Version            Description
 *---------------------------------------------------------*
 * 2018年3月2日      yuan           v1.0.0               创建
 */
@SpringBootConfiguration
public class MQConsumerConfiguration {
    public static final Logger LOGGER = LoggerFactory.getLogger(MQConsumerConfiguration.class);
    //    @Value("${plarform.rocketmq.consumer.namesrvAddr}")
//    public String namesrvAddr;
//    @Value("${plarform.rocketmq.consumer.groupName}")
//    private String groupName = "transferplatform";
//    @Value("${plarform.rocketmq.consumer.consumeThreadMin}")
//    private int consumeThreadMin = 20;
//    @Value("${plarform.rocketmq.consumer.consumeThreadMax}")
//    private int consumeThreadMax = 64;
//    @Value("${plarform.rocketmq.consumer.topics}")
//    private String topics = "DemoTopic~*";
//    @Value("${plarform.rocketmq.consumer.consumeMessageBatchMaxSize}")
//    private int consumeMessageBatchMaxSize = 1;
    @Value("${plarform.server}")
    public String TRANSFER_PLATFORM;

    public String namesrvAddr;

    private String groupName = "transferplatform";

    private int consumeThreadMin = 20;

    private int consumeThreadMax = 64;

    private String topics = "DemoTopic~*";

    private int consumeMessageBatchMaxSize = 1;

    @Autowired
    private MQConsumeMsgListenerProcessor mqMessageListenerProcessor;

    private MyRequestUtils myRequestUtils = new MyRequestUtils();

    public void getConsumerInfo() {
        if (ObjectUtil.isNull(TransferConfig.MQ_CONSUMER_CONFIG_BEAN)) {
            TransferConfig.TRANSFER_PLATFORM =TRANSFER_PLATFORM;
            try {
                Console.log("SDK加载MQ配置文件");
                String resultJson = myRequestUtils.myRequestGet("/transfer/sys/config/sdk");
                JSONObject jsonObject = JSONUtil.parseObj(resultJson);
                TransferConfig.MQ_CONSUMER_CONFIG_BEAN =
                    jsonObject.getJSONObject("consumer").toBean(MQConsumerConfigBean.class);
                TransferConfig.MQ_PRODUCER_CONFIG_BEAN =
                    jsonObject.getJSONObject("producer").toBean(MQProducerConfigBean.class);
                Console.log(jsonObject);
                getMqConsumer();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            getMqConsumer();
        }
    }

    public void getMqConsumer() {
        try {
            namesrvAddr = TransferConfig.MQ_CONSUMER_CONFIG_BEAN.getNamesrvAddr();
            groupName = TransferConfig.MQ_CONSUMER_CONFIG_BEAN.getGroupName();
            consumeThreadMin = TransferConfig.MQ_CONSUMER_CONFIG_BEAN.getConsumeThreadMin();
            consumeThreadMax = TransferConfig.MQ_CONSUMER_CONFIG_BEAN.getConsumeThreadMax();
            topics = TransferConfig.MQ_CONSUMER_CONFIG_BEAN.getTopics();
            consumeMessageBatchMaxSize = TransferConfig.MQ_CONSUMER_CONFIG_BEAN.getConsumeMessageBatchMaxSize();
            Console.log("MQConsumer加载成功！");
        } catch (RuntimeException e) {
            Console.log("MQConsumer加载失败！");
            e.printStackTrace();
        }
    }

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer() {
        getConsumerInfo();
        if (StringUtils.isEmpty(groupName)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL,"groupName is null !!!",false);
        }
        if (StringUtils.isEmpty(namesrvAddr)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL,"namesrvAddr is null !!!",false);
        }
        if(StringUtils.isEmpty(topics)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL,"topics is null !!!",false);
        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(mqMessageListenerProcessor);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
        //consumer.setMessageModel(MessageModel.CLUSTERING);
        /**
         * 设置一次消费消息的条数，默认为1条
         */
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        consumer.setVipChannelEnabled(false);
        try {
            /**
             * 设置该消费者订阅的主题和tag，如果是订阅该主题下的所有tag，则tag使用*；如果需要指定订阅该主题下的某些tag，则使用||分割，例如tag1||tag2||tag3
             */
            String[] topicTagsArr = topics.split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe(topicTag[0],topicTag[1]);
            }
            consumer.start();
            LOGGER.info("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}",groupName,topics,namesrvAddr);
        } catch (MQClientException e) {
            LOGGER.error("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}",groupName,topics,namesrvAddr,e);
            throw new RocketMQException(e);
        }
        return consumer;
    }
}