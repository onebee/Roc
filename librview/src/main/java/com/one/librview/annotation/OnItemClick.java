package com.one.librview.annotation;

import com.one.librview.rview.RView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnItemClickListener", listenerType = RView.OnItemClickListener.class, callBackListener = "onItemClick")
public @interface OnItemClick {

    int[] value();
}
