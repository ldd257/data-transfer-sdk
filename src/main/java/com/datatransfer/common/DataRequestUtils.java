package com.datatransfer.common;


import org.springframework.beans.factory.annotation.Value;


public class DataRequestUtils {

    @Value("${plarform.server.url}")
    public String url;
    @Value("${plarform.server.port}")
    private String port;

}
