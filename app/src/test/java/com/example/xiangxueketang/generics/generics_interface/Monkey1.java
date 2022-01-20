package com.example.xiangxueketang.generics.generics_interface;

import com.example.xiangxueketang.generics.entity.BananaEntity;

public class Monkey1 implements OperateInterface<BananaEntity>{
    @Override
    public void eat(BananaEntity data) {
        System.out.println(Monkey1.class.getSimpleName()+":"+data.toString());
    }
}
