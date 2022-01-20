package com.example.xiangxueketang.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class MealTimeType {

    public static final int BREAK_FAST_TIME = 1;
    public static final int LAUNCH_TIME = 2;
    public static final int DINNER_TIME = 2;

    @IntDef(value = {MealTimeType.BREAK_FAST_TIME,MealTimeType.LAUNCH_TIME,MealTimeType.DINNER_TIME})
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.SOURCE)
    public @interface MealTime {

    }

    public void setMealTimeType(@MealTime int type){
        System.out.println("type:"+type);
    }
}
