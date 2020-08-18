package com.clouds.common.rocketmq.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "TransferConfig")
public class TransferConfig {
    @Value("${plarform.server.url}")
    public String TRANFER_PLATFORM_URL = "http://39.105.97.133";
    @Value("${plarform.server.port}")
    public String TRANFER_PLATFORM_PORT = "8098";
}
