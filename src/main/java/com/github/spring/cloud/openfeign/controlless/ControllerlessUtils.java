package com.github.spring.cloud.openfeign.controlless;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.jar.asm.Opcodes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author wangyongxu
 */
public class ControllerlessUtils {

    public static Class<?> createClass(ControllerClassDescriptor classDescriptor) {
        return createUnloaded(classDescriptor)
                .load(ControllerlessUtils.class.getClassLoader())
                .getLoaded();

    }

    public static DynamicType.Unloaded createUnloaded(ControllerClassDescriptor classDescriptor) {
        AnnotationDescription restControllerAnnotationDescription = AnnotationDescription
                .Builder
                .ofType(RestController.class)
                .build();

        AnnotationDescription requestMappingAnnotationDescription = AnnotationDescription
                .Builder
                .ofType(RequestMapping.class)
                .define("name", classDescriptor.getClazzName())
                .build();

        DynamicType.Builder<?> builder = new ByteBuddy()
                .makeInterface()
//                .subclass(Object.class)
                .name(classDescriptor.getTargetClass().getSimpleName() + "Controller")
                .annotateType(restControllerAnnotationDescription, requestMappingAnnotationDescription);

//        builder.annotateType(restControllerAnnotationDescription, requestMappingAnnotationDescription);
        defineMethods(builder, classDescriptor);
        return builder.make();
    }

    public static Map<TypeDescription, File> createClassToFile(ControllerClassDescriptor classDescriptor, File file) throws IOException {
        return createUnloaded(classDescriptor).saveIn(file);
    }

    private static void defineMethods(DynamicType.Builder<?> builder, ControllerClassDescriptor classDescriptor) {
        for (ControllerMethodDescriptor methodDescriptor : classDescriptor.getMethodDescriptors()) {
            builder.defineMethod(methodDescriptor.getMethodName(), methodDescriptor.getReturnType(), Visibility.PUBLIC);
        }
    }

}
