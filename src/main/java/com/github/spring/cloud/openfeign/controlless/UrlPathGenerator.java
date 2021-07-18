package com.github.spring.cloud.openfeign.controlless;

/**
 * url path 生成器
 *
 * @author wangyongxu
 */
public interface UrlPathGenerator {

    String generatePath(ControllerClassDescriptor classDescriptor, ControllerMethodDescriptor methodDescriptor);


}
