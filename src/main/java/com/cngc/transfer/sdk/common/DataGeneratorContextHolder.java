package com.cngc.transfer.sdk.common;


import org.springframework.stereotype.Component;

@Component
public class DataGeneratorContextHolder {

  public static DataGeneratorContext getContext(){

    return new DataGeneratorContext();
  }
}
