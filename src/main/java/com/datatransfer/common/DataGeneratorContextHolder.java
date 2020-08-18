package com.datatransfer.common;


import org.springframework.stereotype.Component;

@Component
public class DataGeneratorContextHolder {

  public static DataGeneratorContext getContext(){

    return new DataGeneratorContext();
  }
}
