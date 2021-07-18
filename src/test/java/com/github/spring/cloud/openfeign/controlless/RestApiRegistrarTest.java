package com.github.spring.cloud.openfeign.controlless;

import com.github.spring.cloud.openfeign.controlless.annotation.ControllerlessApi;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class RestApiRegistrarTest {

    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        Assertions.assertEquals(dynamicType.newInstance().toString(), "Hello World!");
    }

    @Test
    public void testAnnotation() throws IllegalAccessException, InstantiationException {
        AnnotationDescription annotationDescription = AnnotationDescription
                .Builder
                .ofType(ControllerlessApi.class)
                .define("value", "not fault value")
                .build();
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .annotateType(annotationDescription)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())

                .getLoaded();

        Assertions.assertEquals(dynamicType.newInstance().toString(), "Hello World!");
        Assertions.assertTrue(dynamicType.isAnnotationPresent(ControllerlessApi.class));
        String value = dynamicType.getAnnotation(ControllerlessApi.class).value();
        Assertions.assertEquals("not fault value", value);
    }

    @Test
    public void test1() throws Exception {
        AnnotationDescription annotationDescription = AnnotationDescription
                .Builder
                .ofType(ControllerlessApi.class)
                .define("value", "not fault value")
                .build();
        new ByteBuddy()
                .subclass(Object.class)
                .annotateType(annotationDescription)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader()).saveIn(new File("./hello"));
    }


}