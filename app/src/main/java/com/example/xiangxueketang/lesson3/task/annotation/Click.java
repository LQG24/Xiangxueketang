package com.example.xiangxueketang.lesson3.task.annotation;

import android.view.View;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventType(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener")
public @interface Click {
    @IdRes int[] value();
}
