package com.clouds.common.rocketmq.aspect;



import com.clouds.common.rocketmq.consumer.MQConsumerConfiguration;
import com.clouds.common.rocketmq.context.DataApplicationContextAware;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author maxD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({DataApplicationContextAware.class, MQConsumerConfiguration.class})
public @interface EnableParallelComputeWorker {
}
