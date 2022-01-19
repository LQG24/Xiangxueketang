package com.example.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlintest.singleton.StaticInnerSingleton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StaticInnerSingleton.instance.show()
    }
}