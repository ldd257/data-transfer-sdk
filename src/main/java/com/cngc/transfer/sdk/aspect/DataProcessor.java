package com.cngc.transfer.sdk.aspect;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface DataProcessor {
    String value();
    boolean require() default true;
}
