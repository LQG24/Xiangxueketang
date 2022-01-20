package com.example.xiangxueketang;

import org.junit.Test;

import com.example.xiangxueketang.generics.generics_class.Animal;
import com.example.xiangxueketang.generics.generics_class.Monkey;
import com.example.xiangxueketang.generics.generics_class.Tiger;
import com.example.xiangxueketang.generics.entity.BananaEntity;
import com.example.xiangxueketang.generics.entity.MeatEntity;
import com.example.xiangxueketang.generics.generics_interface.Monkey1;
import com.example.xiangxueketang.generics.generics_interface.Tiger1;
import com.example.xiangxueketang.generics.generics_method.FoodManager;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GenericsTest {
    @Test
    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
        BananaEntity bananaEntity = new BananaEntity("banana",10);
        MeatEntity meatEntity = new MeatEntity("meat",15);

        //泛型类
        Animal<BananaEntity> animal = new Monkey(bananaEntity);
        animal.eat();

        Animal<MeatEntity> animal1 = new Tiger(meatEntity);
        animal1.eat();

        //泛型接口
        Monkey1 monkey1 = new Monkey1();
        monkey1.eat(bananaEntity);

        Tiger1 tiger1 = new Tiger1();
        tiger1.eat(meatEntity);


        FoodManager foodManager = new FoodManager();
        foodManager.getFood(bananaEntity);
        foodManager.getFood(meatEntity);

    }
}



