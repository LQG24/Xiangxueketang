package com.example.xiangxueketang.lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.xiangxueketang.R;
import com.example.xiangxueketang.lesson2.annotation.InjectView;
import com.example.xiangxueketang.lesson2.utils.InjectViewUtils;

public class MainActivity extends AppCompatActivity {
    //模仿BufferKnife获取控件id
    @InjectView(R.id.text_view)
     TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectViewUtils.injectView(this);
        mTextView.setText("模仿BufferKnife获取控件id");
    }
}