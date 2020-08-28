package com.one.librview.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
// 该注解作用于类，接口或者枚举类型上
@Target(ElementType.TYPE)
// 注解会在class字节码文件中存在，jvm加载时可以通过反射获取到该注解的内容
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {

    // int类型布局
    int value();

}
