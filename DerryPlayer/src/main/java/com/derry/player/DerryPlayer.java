package com.derry.player;

import android.media.MediaPlayer;

public class DerryPlayer {

    static {
        System.loadLibrary("native-lib");
    }

    private OnPreparedListener onPreparedListener; // C++层准备情况的接口

    public DerryPlayer() {
    }

    // 媒体源（文件路径， 直播地址rtmp）
    private String dataSource;

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 播放前的 准备工作
     */
    public void prepare() {
        prepareNative(dataSource);
    }

    /**
     * 开始播放
     */
    public void start() {
        startNative();
    }

    /**
     * 停止播放
     */
    public void stop() {
        stopNative();
    }

    /**
     * 释放资源
     */
    public void release() {
        releaseNative();
    }

    /**
     * 给jni反射调用的
     */
    public void onPrepared() {
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared();
        }
    }

    /**
     * 设置准备OK的监听方法
     */
    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.onPreparedListener = onPreparedListener;
    }

    /**
     * 准备OK的监听
     */
    public interface OnPreparedListener {
         void onPrepared();
    }

    // TODO >>>>>>>>>>> 下面是native函数区域
    private native void prepareNative(String dataSource);
    private native void startNative();
    private native void stopNative();
    private native void releaseNative();
}
