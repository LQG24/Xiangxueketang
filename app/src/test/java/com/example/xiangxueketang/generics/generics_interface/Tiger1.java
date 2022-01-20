package com.example.xiangxueketang.generics.generics_interface;

import com.example.xiangxueketang.generics.entity.MeatEntity;

public class Tiger1 implements OperateInterface<MeatEntity>{
    @Override
    public void eat(MeatEntity data) {
        System.out.println(Tiger1.class.getSimpleName()+":"+data.toString());
    }
}
