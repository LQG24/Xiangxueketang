package com.example.xiangxueketang.lesson3.task;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.xiangxueketang.lesson3.task.annotation.EventType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectUtils {

    public static void inject(Activity activity) {
        Class<? extends Activity> activityClass = activity.getClass();
        Method[] methods = activityClass.getDeclaredMethods();
        for (Method method : methods) {
            //获得方法上所有注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //注解类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isAnnotationPresent(EventType.class)) {
                    EventType eventType = annotationType.getAnnotation(EventType.class);

                    // OnClickListener.class
                    Class listenerType = eventType.listenerType();
                    //setOnClickListener
                    String listenerSetter = eventType.listenerSetter();

                    try {
                        //public abstract int[] com.example.xiangxueketang.lesson3.task.annotation.Click.value()
                        //获取Click接口对应的value方法
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        int[] viewIds = (int[]) valueMethod.invoke(annotation);
                        method.setAccessible(true);
                        ListenerInvocationHandler<Activity> handler = new ListenerInvocationHandler(activity, method);
                        Object listenerProxy = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);

                        // 遍历注解的值
                        for (int viewId : viewIds) {
                            // 获得当前activity的view（赋值）
                            View view = activity.findViewById(viewId);
                            // 如获得：setOnClickLisnter方法，参数为OnClickListener
                            Method setter = view.getClass().getMethod(listenerSetter,listenerType);
                            setter.invoke(view,listenerProxy);
                        }
                    } catch (Exception e) {
                        Log.e("InjectUtils",e.getMessage());
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    /**
     * 还可能在自定义view注入，所以是泛型： T = Activity/View
     * 用动态代理 把public abstract void android.view.View$OnClickListener.onClick(android.view.View)
     * 代替为public void com.example.xiangxueketang.lesson3.task.Lesson3Activity.abc(android.view.View)
     * @param <T>
     */
    private static class ListenerInvocationHandler<T> implements InvocationHandler {
        private T mTarget;
        private Method mMethod;

        public ListenerInvocationHandler(T target, Method method) {
            mTarget = target;
            mMethod = method;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.mMethod.invoke(mTarget, args);
        }
    }
}
