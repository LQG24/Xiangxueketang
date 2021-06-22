package com.example.xiangxueketang.lesson2.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.xiangxueketang.R;
import com.example.xiangxueketang.lesson2.annotation.InjectIntent;
import com.example.xiangxueketang.lesson2.utils.InjectIntentUtil;

public class SecondActivity extends AppCompatActivity {

    @InjectIntent
    private String name;

    @InjectIntent("No.")
    private int mNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        InjectIntentUtil.inject(this);
        ((TextView)findViewById(R.id.name_tv)).setText(name+":"+mNo);
    }
}