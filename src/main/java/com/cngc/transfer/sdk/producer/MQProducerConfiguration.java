package com.cngc.transfer.sdk.producer;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.common.MyRequestUtils;
import com.cngc.transfer.sdk.common.TransferConfig;
import com.cngc.transfer.sdk.constants.RocketMQErrorEnum;
import com.cngc.transfer.sdk.consumer.MQConsumerConfigBean;
import com.cngc.transfer.sdk.exception.RocketMQException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

/**
 * 生产者配置
 * .<br/>
 * 
 * Copyright: Copyright (c) 2017  zteits
 * 
 * @ClassName: MQProducerConfiguration
 * @Description: 
 * @version: v1.0.0
 * @author: zhaowg
 * @date: 2018年3月2日 下午11:44:36
 * Modification History:
 * Date             Author          Version            Description
 *---------------------------------------------------------*
 * 2018年3月2日      zhaowg           v1.0.0               创建
 */
@SpringBootConfiguration
public class MQProducerConfiguration {
    public static final Logger LOGGER = LoggerFactory.getLogger(MQProducerConfiguration.class);
    /**
     * 发送同一类消息的设置为同一个group，保证唯一,默认不需要设置，rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
     */
    private String groupName;
    private String namesrvAddr;
    /**
     * 消息最大大小，默认4M
     */
    private Integer maxMessageSize ;
    /**
     * 消息发送超时时间，默认3秒
     */
    private Integer sendMsgTimeout;
    /**
     * 消息发送失败重试次数，默认2次
     */
    private Integer retryTimesWhenSendFailed;

    @Value("${plarform.server}")
    public String TRANSFER_PLATFORM;
    private MyRequestUtils myRequestUtils = new MyRequestUtils();

    public void getProducerInfo() {
        if (ObjectUtil.isNull(TransferConfig.MQ_PRODUCER_CONFIG_BEAN)) {
            TransferConfig.TRANSFER_PLATFORM = TRANSFER_PLATFORM;
            try {
                Console.log("SDK加载MQ配置文件");
                String resultJson = myRequestUtils.myRequestGet("/transfer/sys/config/sdk");
                JSONObject jsonObject = JSONUtil.parseObj(resultJson);
                TransferConfig.MQ_CONSUMER_CONFIG_BEAN = jsonObject.getJSONObject("consumer").toBean(MQConsumerConfigBean.class);
                TransferConfig.MQ_PRODUCER_CONFIG_BEAN = jsonObject.getJSONObject("producer").toBean(MQProducerConfigBean.class);
                Console.log(jsonObject);
                getMqProducer();
            }
            catch (RuntimeException e){
                e.printStackTrace();
            }
        } else {
            getMqProducer();
        }
    }

    public void getMqProducer() {
        try {
            namesrvAddr = TransferConfig.MQ_PRODUCER_CONFIG_BEAN.getNamesrvAddr();
            groupName = TransferConfig.MQ_PRODUCER_CONFIG_BEAN.getGroupName();
            maxMessageSize = TransferConfig.MQ_PRODUCER_CONFIG_BEAN.getMaxMessageSize();
            sendMsgTimeout = TransferConfig.MQ_PRODUCER_CONFIG_BEAN.getSendMsgTimeout();
            retryTimesWhenSendFailed = TransferConfig.MQ_PRODUCER_CONFIG_BEAN.getRetryTimesWhenSendFailed();
            Console.log("MQProducer加载成功！");
        } catch (RuntimeException e) {
            Console.log("MQProducer加载失败！");
            e.printStackTrace();
        }
    }

    @Bean
    public DefaultMQProducer getRocketMQProducer() throws RocketMQException {
        getProducerInfo();
        if (StringUtils.isEmpty(this.groupName)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL,"groupName is blank",false);
        }
        if (StringUtils.isEmpty(this.namesrvAddr)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL,"nameServerAddr is blank",false);
        }
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.namesrvAddr);
        //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
        //producer.setInstanceName(instanceName);
        if(this.maxMessageSize != null) {
        	producer.setMaxMessageSize(this.maxMessageSize);
        }
        if(this.sendMsgTimeout != null) {
        	producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if(this.retryTimesWhenSendFailed != null) {
        	producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }
        try {
            producer.start();
            LOGGER.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]"
                    , this.groupName, this.namesrvAddr));
        } catch (MQClientException e) {
            LOGGER.error(String.format("producer is error {}"
                    , e.getMessage(),e));
            throw new RocketMQException(e);
        }
        return producer;
    }
}