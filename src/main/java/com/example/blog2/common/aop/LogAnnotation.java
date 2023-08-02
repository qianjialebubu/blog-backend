package com.example.blog2.common.aop;

import java.lang.annotation.*;

/**
 * @author qjl
 * @create 2023-07-31 17:29
 * @deprecated method代表可以放在方法上，type代表可以放在类上
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";
    String operator() default "";
}
