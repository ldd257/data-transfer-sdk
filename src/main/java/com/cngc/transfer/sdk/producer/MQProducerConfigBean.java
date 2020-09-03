package com.cngc.transfer.sdk.producer;

import lombok.Data;

@Data
public class MQProducerConfigBean {

    private String isOnOff;

    private String groupName;

    private String namesrvAddr;

    private int maxMessageSize;

    private int sendMsgTimeout;

    private int retryTimesWhenSendFailed;
}
