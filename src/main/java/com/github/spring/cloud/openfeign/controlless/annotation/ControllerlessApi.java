package com.github.spring.cloud.openfeign.controlless.annotation;

import java.lang.annotation.*;

/**
 * build rest api based class name and method name
 *
 * @author wangyongxu
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Target(value = ElementType.TYPE)
public @interface ControllerlessApi {

    String value() default "hello";

    /**
     * return包装类
     */
    Class<?> returnWrapper() default void.class;

    Class<?> returnPolicy() default void.class;

}
