package com.example.xiangxueketang.lesson2.utils;

import android.app.Activity;
import android.text.TextUtils;

import com.example.xiangxueketang.lesson2.annotation.InjectIntent;

import java.lang.reflect.Field;

public class InjectIntentUtil {
    public static void inject(Activity activity) {
        Class<? extends Activity> activityClass = activity.getClass();
        try {
            Field[] fields = activityClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(InjectIntent.class)) {
                    InjectIntent injectIntent = field.getAnnotation(InjectIntent.class);
                    String key = injectIntent.value();
                    if(TextUtils.isEmpty(key)){
                        field.setAccessible(true);
                        String keyName = field.getName();
                        String name =activity.getIntent().getStringExtra(keyName);
                        field.set(activity, name);
                    }else {
                        int  age = activity.getIntent().getIntExtra(key,0);
                        field.setAccessible(true);
                        field.set(activity, age);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
