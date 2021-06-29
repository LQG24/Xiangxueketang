package com.example.xiangxueketang.lesson3.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.xiangxueketang.R;
import com.example.xiangxueketang.lesson3.task.annotation.Click;

/**
 * 利用反射、注解、动态代理实现OnClick事件的自动注入
 *
 * @Click({R.id.bottom,R.id.end}) public void abc(View view){
 * <p>
 * }
 */
public class Lesson3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);
        InjectUtils.inject(this);
    }

    @Click({R.id.btn1, R.id.btn2})
    public void abc(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Log.d(Lesson3Activity.class.getSimpleName(), "btn1");
                break;
            case R.id.btn2:
                Log.d(Lesson3Activity.class.getSimpleName(), "btn2");
                break;
        }
    }
}