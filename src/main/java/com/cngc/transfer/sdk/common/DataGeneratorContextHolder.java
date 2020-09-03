package com.cngc.transfer.sdk.common;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DataGeneratorContextHolder implements ApplicationContextAware {


  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext app) {
    DataGeneratorContextHolder.applicationContext = app;
  }

  public static DataGeneratorContext getContext() {
    return applicationContext.getBean(DataGeneratorContext.class);
  }
}
