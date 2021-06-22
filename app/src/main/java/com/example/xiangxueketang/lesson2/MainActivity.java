package com.example.xiangxueketang.lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiangxueketang.R;
import com.example.xiangxueketang.lesson2.annotation.InjectView;
import com.example.xiangxueketang.lesson2.task.SecondActivity;
import com.example.xiangxueketang.lesson2.utils.InjectViewUtils;

public class MainActivity extends AppCompatActivity {
    //模仿BufferKnife获取控件id
    @InjectView(R.id.text_view)
     TextView mTextView;
    @InjectView(R.id.btn)
    Button mButton;

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
    }
}