package com.cngc.transfer.sdk.consumer;

import lombok.Data;

@Data
public class MQConsumerConfigBean {

    private String isOnOff;

    private String groupName;

    private String namesrvAddr;

    private String topics;

    private int consumeThreadMin;

    private int consumeThreadMax;

    private int consumeMessageBatchMaxSize;
}
