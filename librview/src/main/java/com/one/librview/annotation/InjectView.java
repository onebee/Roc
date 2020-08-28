package com.one.librview.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
@Target(ElementType.FIELD) // 属性上
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {

    int value();
}
