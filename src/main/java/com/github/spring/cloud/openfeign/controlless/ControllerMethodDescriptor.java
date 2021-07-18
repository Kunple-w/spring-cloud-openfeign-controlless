package com.github.spring.cloud.openfeign.controlless;

import java.lang.reflect.Method;

/**
 * @author wangyongxu
 */
public class ControllerMethodDescriptor {

    private ControllerClassDescriptor classDescriptor;

    private Method method;

    private String methodName;

    private Class<?> returnType;


    private Object[] parameterTypes;

    public ControllerClassDescriptor getClassDescriptor() {
        return classDescriptor;
    }

    public void setClassDescriptor(ControllerClassDescriptor classDescriptor) {
        this.classDescriptor = classDescriptor;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameterTypes() {
        return parameterTypes;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public void setParameterTypes(Object[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
