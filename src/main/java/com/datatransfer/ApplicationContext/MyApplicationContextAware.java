package com.datatransfer.ApplicationContext;

import com.datatransfer.aspect.DataProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyApplicationContextAware implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		MyApplicationContextAware.applicationContext = applicationContext;
	}
	
	/**
	 * 获取添加了@DataProcessor注解的类
	 * @return
	 */
	public static Map<String, Object> getApplicationContext(){
		Map<String, Object> beansWithAnnotationMap = applicationContext.getBeansWithAnnotation(DataProcessor.class);
		return beansWithAnnotationMap;  
	}
	

}
