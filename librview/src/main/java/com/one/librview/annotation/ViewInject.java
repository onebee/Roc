package com.one.librview.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
@Target(ElementType.FIELD) // 该注解作用于属性、枚举的常量
@Retention(RetentionPolicy.RUNTIME) // jvm运行时通过反射获取到该注解的内容
public @interface ViewInject {

    int value();
}