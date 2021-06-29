package com.example.xiangxueketang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiangxueketang.lesson2.annotation.InjectView;
import com.example.xiangxueketang.lesson2.task_lesson_2.SecondActivity;
import com.example.xiangxueketang.lesson2.utils.InjectViewUtils;
import com.example.xiangxueketang.lesson3.task.Lesson3Activity;

public class MainActivity extends AppCompatActivity {
    //模仿BufferKnife获取控件id
    @InjectView(R.id.text_view)
     TextView mTextView;
    @InjectView(R.id.btn)
    Button mButton;

    @InjectView(R.id.btn1)
    Button mToLesson3Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectViewUtils.injectView(this);
        mTextView.setText("模仿BufferKnife获取控件id");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name","kobe");
                intent.putExtra("No.",24);
                startActivity(intent);
            }
        });

        mToLesson3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson3Activity.class));
            }
        });
    }
}