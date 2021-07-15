package com.github.spring.cloud.openfeign.controlless.annotation;

import java.lang.annotation.*;

/**
 * build rest api based class name and method name
 * @author wangyongxu
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Target(value = ElementType.TYPE)
public @interface RestApi {
}
