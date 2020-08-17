package com.datatransfer.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
public class TransferConfig {
    @Value("${plarform.server.url}")
    public String TRANFER_PLATFORM_URL;
    @Value("${plarform.server.port}")
    public String TRANFER_PLATFORM_PORT;
}
