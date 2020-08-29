package com.one.librview;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
// 将回调的onClick方法拦截，执行我们自己自定义的方法（aop概念）
public class ListenerInvocationHandler implements InvocationHandler {

    // 需要拦截的对象
    private Object target;

    // 需要拦截的对象键值对
    private HashMap<String, Method> methodHashMap = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target != null) {
            // 获取需要拦截的方法名
            String methodName = method.getName(); // 假如是onClick
            Log.i(Common.TAG, "拦截的方法名 : " + methodName);
            // 重新赋值，将拦截的方法换为show
            method = methodHashMap.get(methodName); // 执行拦截的方法，show
            Log.i(Common.TAG, "替换执行的的方法名 : " + method);
            Log.i(Common.TAG, "args = " + args);
            if (method != null) {
                return method.invoke(target, args);
            }
        }
        return null;
    }

    /**
     * 将需要拦截的方法添加
     *
     * @param methodName 需要拦截的方法，如：onClick()
     * @param method     执行拦截后的方法，如：show()
     */
    public void addMethod(String methodName, Method method) {
        methodHashMap.put(methodName, method);
    }
}
