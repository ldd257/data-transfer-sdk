package com.clouds.common.rocketmq.context;

import java.util.Map;

import com.clouds.common.rocketmq.aspect.DataProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DataApplicationContextAware implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		DataApplicationContextAware.applicationContext = applicationContext;
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
