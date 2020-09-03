package com.cngc.transfer.sdk.common;


import com.cngc.transfer.sdk.consumer.MQConsumerConfigBean;
import com.cngc.transfer.sdk.producer.MQProducerConfigBean;


/**
 * TransferConfig class
 *
 * @author sun
 * @date 2020/08/28
 */
public class TransferConfig {

    public static String TRANSFER_PLATFORM;
    public static MQConsumerConfigBean MQ_CONSUMER_CONFIG_BEAN;
    public static MQProducerConfigBean MQ_PRODUCER_CONFIG_BEAN;
    public static String TRANSFER_PLATFORM_TOPIC = "serverTopic";
    public static String TRANSFER_PLATFORM_TAGS = "updateProcess";

}
