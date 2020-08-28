package com.one.librview;

import android.view.View;

import com.one.librview.annotation.ContentView;
import com.one.librview.annotation.EventBase;
import com.one.librview.annotation.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import androidx.appcompat.app.AppCompatActivity;


/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
public class InjectManager {

    public static void inject(AppCompatActivity act) {
        // 布局的注入
        injectLayout(act);
        // 控件的注入
        injectViews(act);
        // 事件的注入
        injectEvents(act);
    }

    public static void injectEvents(AppCompatActivity act) {
        Class<? extends AppCompatActivity> clazz = act.getClass();
        // 获取类的所有方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 获取每个方法的注解（多个控件id）
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                // 获取注解上的注解
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    // 通过EventBase指定获取
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    if (eventBase != null) { // 有些方法没有EventBase注解
                        // 事件3大成员
                        String listenerSetter = eventBase.listenerSetter();
                        Class<?> listenerType = eventBase.listenerType();
                        String callBackListener = eventBase.callBackListener();
                        try {
                            // 通过annotationType 获取 onClick 注解的value值
                            Method valueMethod = annotationType.getDeclaredMethod("value");
                            // 执行value方法获得注解的值
                            int[] viewIds = (int[]) valueMethod.invoke(annotation);

                            // 代理方式（3个成员组合）
                            // 拦截方法
                            // 得到监听的代理对象（新建代理单例、类的加载器，指定要代理的对象类的类型、class实例）
                            ListenerInvocationHandler handler = new ListenerInvocationHandler(act);

                            // 添加到拦截列表里面
                            handler.addMethod(callBackListener, method);
                            // 监听对象的代理对象
                            // ClassLoader loader:指定当前目标对象使用类加载器,获取加载器的方法是固定的
                            // Class<?>[] interfaces:目标对象实现的接口的类型,使用泛型方式确认类型
                            // InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法
                            Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);

                            // 遍历注解的值
                            for (int viewId : viewIds) {
                                // 获得当前activity的view（赋值）
                                View view = act.findViewById(viewId);
                                // 获取指定的方法
                                Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                                // 执行方法
                                setter.invoke(view, listener);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static void injectViews(AppCompatActivity act) {
        Class<? extends AppCompatActivity> clazz = act.getClass();
        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 循环每个属性
        for (Field field : fields) {
            // 获得属性上的注解
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                // 获取注解值
                int viewId = injectView.value();
                // 获取findViewById方法，并执行
                try {
                    Method method = clazz.getMethod("findViewById", int.class);

                    Object view = method.invoke(act, viewId);
                    // 设置访问权限private
                    field.setAccessible(true);
                    field.set(act, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectLayout(AppCompatActivity act) {
        Class<? extends AppCompatActivity> clazz = act.getClass();
        // 获取类的注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int layoutId = contentView.value();
            // 获取Activity指定方法
            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.invoke(act, layoutId);
                // 另一种写法：
                // act.setContentView(layoutId);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
