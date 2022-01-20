package com.example.xiangxueketang.generics.generics_class;

import com.example.xiangxueketang.generics.entity.BananaEntity;

/**
 * 猴子吃香蕉
 */
public class Monkey extends Animal<BananaEntity> {
    public Monkey(BananaEntity food) {
        super(food);
    }

    @Override
    public String getName() {
        return Monkey.class.getSimpleName();
    }

}
