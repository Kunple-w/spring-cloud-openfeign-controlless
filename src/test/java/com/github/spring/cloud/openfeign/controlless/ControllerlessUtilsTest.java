package com.github.spring.cloud.openfeign.controlless;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class ControllerlessUtilsTest {

    @Test
    void create() {
        ControllerClassDescriptor classDescriptor = new ControllerDescriptorBuilder(UserService.class).build();
        Class<?> aClass = ControllerlessUtils.createClass(classDescriptor);
    }

    @Test
    void createClass() {
    }

    @Test
    void createUnloaded() {
    }

    @Test
    void createClassToFile() throws IOException {
        ControllerClassDescriptor classDescriptor = new ControllerDescriptorBuilder(UserService.class).build();
        ControllerlessUtils.createClassToFile(classDescriptor,new File("./xxx/a"));

    }
}