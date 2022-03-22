package com.chen.lib_processor;

import com.chen.lib_annotations.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * 注解处理器
 */
public class BindingProcessor extends AbstractProcessor {

    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //System.out.println("process run!");
        for (Element element: roundEnv.getRootElements()) {
            String packageStr = element.getEnclosingElement().toString();// 包名
            String classStr = element.getSimpleName().toString();// 类名
            ClassName className = ClassName.get(packageStr, classStr + "Binding");
            MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ClassName.get(packageStr, classStr), "activity");

            boolean hasBinding = false;
            for (Element element2 : element.getEnclosedElements()) {
                if (element2.getKind() == ElementKind.FIELD) {// 是加了注解的字段
                    BindView bindView = element2.getAnnotation(BindView.class);// 获取注解
                    if (bindView != null) {
                        hasBinding = true;
                        constructorBuilder.addStatement("activity.$N = activity.findViewById($L)"
                                , element2.getSimpleName(), bindView.value());
                    }
                }
            }

            TypeSpec classBuilder = TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(constructorBuilder.build())
                    .build();
            if (hasBinding) {
                try {
                    JavaFile.builder(packageStr, classBuilder)
                            .build()
                            .writeTo(filer);// 构造出文件并写到指定位置
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(BindView.class.getCanonicalName());
    }
}