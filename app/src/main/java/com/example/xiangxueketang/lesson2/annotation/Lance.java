package com.example.xiangxueketang.lesson2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@interface Lance {
    String value();//无默认值,在使用注解时, 如果定义的注解中的类型元素无默认值，则必须进行传值。

    int age() default 1;//有默认值
}

class Test {
//    @Lance("帅气")  //如果只存在value元素需要传值的情况，则可以省略:元素名=
    @Lance(age = 2, value = "老了")
            int i;

}



