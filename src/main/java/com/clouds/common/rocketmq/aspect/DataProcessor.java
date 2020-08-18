package com.clouds.common.rocketmq.aspect;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataProcessor {
    String value();
    boolean require() default true;
}
