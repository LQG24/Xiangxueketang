package com.example.xiangxueketang.generics.generics_method;

public class FoodManager {

    /**
     * 泛型方法
     */
    public <T>void getFood(T food){
        System.out.println("FoodManager:"+food.toString());
    }
}
