package com.derry.player;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.WindowManager;
import android.widget.Toast;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private DerryPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        player = new DerryPlayer();
        player.setDataSource(
                new File(Environment.getExternalStorageDirectory() + File.separator + "demo.mp4")
                        .getAbsolutePath());
        // 准备成功的回调处    <----  C++ 子线程调用的
        player.setOnPreparedListener(new DerryPlayer.OnPreparedListener() {
            @Override
            public void onPrepared() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "准备成功，即将开始播放", Toast.LENGTH_SHORT).show();
                    }
                });
                player.start(); // 调用 C++ 开始播放
            }
        });
    }

    @Override // ActivityThread.java Handler
    protected void onResume() { // 我们的准备工作：触发
        super.onResume();
        player.prepare();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
