package com.datatransfer.aspect;


import com.datatransfer.ApplicationContext.MyApplicationContextAware;
import com.datatransfer.consumer.MQConsumerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启分布式并行计算worker节点.
 *
 * @author maxD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({MyApplicationContextAware.class, MQConsumerConfiguration.class})
public @interface EnableParallelComputeWorker {
}
