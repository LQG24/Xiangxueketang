package com.example.xiangxueketang.generics.generics_class;

import com.example.xiangxueketang.generics.entity.MeatEntity;

public class Tiger extends Animal<MeatEntity> {
    public Tiger(MeatEntity food) {
        super(food);
    }

    @Override
   public String getName() {
        return Tiger.class.getSimpleName();
    }
}
