package com.github.spring.cloud.openfeign.controlless;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
                .define("name", "/" + classDescriptor.getTargetClass().getSimpleName())
                .build();

        AnnotationDescription feignClient = AnnotationDescription
                .Builder
                .ofType(FeignClient.class)
                .build();

        DynamicType.Builder<?> builder = new ByteBuddy()
                .makeInterface()
                .name(classDescriptor.getTargetClass().getName() + "Controller")
                .annotateType(restControllerAnnotationDescription, requestMappingAnnotationDescription, feignClient);
        builder = defineMethods(builder, classDescriptor);
        return builder.make();
    }

    public static Map<TypeDescription, File> createClassToFile(ControllerClassDescriptor classDescriptor, File file) throws IOException {
        return createUnloaded(classDescriptor).saveIn(file);
    }

    private static DynamicType.Builder<?> defineMethods(DynamicType.Builder<?> builder, ControllerClassDescriptor classDescriptor) {
        for (ControllerMethodDescriptor methodDescriptor : classDescriptor.getMethodDescriptors()) {
            AnnotationDescription annotationDescription = AnnotationDescription
                    .Builder
                    .ofType(PostMapping.class)
                    .define("name", "/" + methodDescriptor.getMethodName())
                    .build();
            DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial<?> initial = builder.defineMethod(methodDescriptor.getMethodName(), methodDescriptor.getReturnType(), Visibility.PUBLIC);

            for (Class<?> parameterType : methodDescriptor.getParameterTypes()) {
                builder = initial.withParameter(parameterType).annotateParameter(AnnotationDescription
                        .Builder
                        .ofType(RequestBody.class)
                        .build())
                        .withoutCode()
                        .annotateMethod(annotationDescription);
            }
        }
        return builder;
    }

}
