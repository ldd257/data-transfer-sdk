package com.cngc.transfer.sdk.aspect;



import com.cngc.transfer.sdk.consumer.MQConsumerConfiguration;
import com.cngc.transfer.sdk.context.DataApplicationContextAware;
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
