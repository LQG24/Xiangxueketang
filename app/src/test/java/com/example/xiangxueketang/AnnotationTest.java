package com.example.xiangxueketang;

import com.example.xiangxueketang.annotation.MealTimeType;

import org.junit.Test;

public class AnnotationTest {
    @Test
    public void test(){
        MealTimeType mealTimeType = new MealTimeType();
        mealTimeType.setMealTimeType(MealTimeType.BREAK_FAST_TIME);
    }
}
