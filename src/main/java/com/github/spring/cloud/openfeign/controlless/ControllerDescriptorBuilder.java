package com.github.spring.cloud.openfeign.controlless;


import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyongxu
 */
public class ControllerDescriptorBuilder {

    private Class<?> clazz;

    public ControllerDescriptorBuilder(Class<?> clazz) {
        this.clazz = clazz;
    }

    public ControllerClassDescriptor build() {
        ControllerClassDescriptor classDescriptor = new ControllerClassDescriptor();
        classDescriptor.setTargetClass(clazz);
        classDescriptor.setInterface(this.clazz.isInterface());
        classDescriptor.setClazzName(clazz.getName());
        classDescriptor.setMethodDescriptors(methodDescriptors());
        return classDescriptor;
    }

    public List<ControllerMethodDescriptor> methodDescriptors() {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
        List<ControllerMethodDescriptor> list = new ArrayList<>();
        for (Method method : methods) {
            ControllerMethodDescriptor methodDescriptor = new ControllerMethodDescriptor();
//            methodDescriptor.setClassDescriptor();
            methodDescriptor.setMethod(method);
            methodDescriptor.setMethodName(method.getName());
            methodDescriptor.setReturnType(method.getReturnType());
            methodDescriptor.setParameterTypes(method.getParameterTypes());
            list.add(methodDescriptor);
        }
        return list;
    }


}
