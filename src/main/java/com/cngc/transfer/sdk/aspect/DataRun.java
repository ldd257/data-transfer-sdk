package com.cngc.transfer.sdk.aspect;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataRun {
    // 允许注解有参数
    Class<?>  value() ;
    boolean require() default true;
}
