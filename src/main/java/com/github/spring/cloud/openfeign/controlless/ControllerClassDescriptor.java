package com.github.spring.cloud.openfeign.controlless;

import java.util.List;

/**
 * @author wangyongxu
 */
public class ControllerClassDescriptor {

    private Class<?> targetClass;

    private boolean isInterface;

    private String clazzName;

    private List<ControllerMethodDescriptor> methodDescriptors;


    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public List<ControllerMethodDescriptor> getMethodDescriptors() {
        return methodDescriptors;
    }

    public void setMethodDescriptors(List<ControllerMethodDescriptor> methodDescriptors) {
        this.methodDescriptors = methodDescriptors;
    }
}
