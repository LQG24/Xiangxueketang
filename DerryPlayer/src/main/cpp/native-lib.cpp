#include <jni.h>
#include <string>
#include "DerryPlayer.h"
#include "JNICallbakcHelper.h"

extern "C"{
    #include <libavutil/avutil.h>
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_derry_player_MainActivity_getFFmpegVersion(
        JNIEnv *env,
        jobject /* this */) {
    std::string info = "FFmpeg的版本号是:";
    info.append(av_version_info());
    return env->NewStringUTF(info.c_str());
}

DerryPlayer *player = 0;
JavaVM *vm = 0;
jint JNI_OnLoad(JavaVM * vm, void * args) {
    ::vm = vm;
    return JNI_VERSION_1_6;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_derry_player_DerryPlayer_prepareNative(JNIEnv *env, jobject job, jstring data_source) {
    const char * data_source_ = env->GetStringUTFChars(data_source, 0);
    auto *helper = new JNICallbakcHelper(vm, env, job); // C++子线程回调 ， C++主线程回调
    player = new DerryPlayer(data_source_, helper);
    player->prepare();
    env->ReleaseStringUTFChars(data_source, data_source_);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_derry_player_DerryPlayer_startNative(JNIEnv *env, jobject thiz) {
    if (player) {
        // player.start(); 下节课 完成视频播放
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_derry_player_DerryPlayer_stopNative(JNIEnv *env, jobject thiz) {
}

extern "C"
JNIEXPORT void JNICALL
Java_com_derry_player_DerryPlayer_releaseNative(JNIEnv *env, jobject thiz) {
}