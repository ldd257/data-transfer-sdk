package com.datatransfer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Component;

@SpringBootConfiguration
public class TransferConfig {
    public   String TRANFER_PLATFORM_URL = "http://localhost";
    public   int TRANFER_PLATFORM_PORT = 8080;


}
