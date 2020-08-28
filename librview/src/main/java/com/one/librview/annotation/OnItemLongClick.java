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
@EventBase(listenerSetter = "setOnItemLongClickListener", listenerType = RView.OnItemLongClickListener.class, callBackListener = "onItemLongClick")
public @interface OnItemLongClick {

    int[] value(); // 数组形式，多id多控件共用某点击方法
}
