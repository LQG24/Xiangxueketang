package com.example.xiangxueketang.generics.generics_class;
/**
 * 泛型类
 */
public class Animal<T>{
    private final T food;

    public Animal(T food) {
        this.food = food;
    }

   public void eat(){
        System.out.println(getName()+":"+food.toString());
    }

   public String getName(){
        return "";
    }
}
