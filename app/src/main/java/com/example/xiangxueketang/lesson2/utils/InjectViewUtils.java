package com.example.xiangxueketang.lesson2.utils;

import android.app.Activity;

import com.example.xiangxueketang.lesson2.annotation.InjectView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InjectViewUtils {
    public static void injectView(Activity activity) {
        if (activity == null) return;
        Class<? extends Activity> activityClass = activity.getClass();
        //获取Activity类里面声明的所有成员变量
        Field[] fields = activityClass.getDeclaredFields();
        for (Field field : fields) {
            //找出标注了@InjectView的成员变量
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                //获取变量的值，也就是控件的id
                int viewId = injectView.value();

                try {
//                    Object view = activity.findViewById(viewId);
                    //找到findViewById方法
                    Method findViewById = activityClass.getMethod("findViewById", int.class);
                    findViewById.setAccessible(true);
                    Object view = findViewById.invoke(activity, viewId);

                    //不设置非public不能获取
                    field.setAccessible(true);
                    field.set(activity, view);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
