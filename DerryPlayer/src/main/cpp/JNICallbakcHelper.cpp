#include "JNICallbakcHelper.h"

JNICallbakcHelper::JNICallbakcHelper(JavaVM *vm, JNIEnv *env, jobject job) {
    this->vm = vm;
    this->env = env;
    // this->job = job; // 坑： jobject不能跨越线程，不能跨越函数，必须全局引用
    this->job = env->NewGlobalRef(job); // 提示全局引用

    jclass clazz = env->GetObjectClass(job);
    jmd_prepared = env->GetMethodID(clazz, "onPrepared", "()V");
}

JNICallbakcHelper::~JNICallbakcHelper() {
    vm = 0;
    env->DeleteGlobalRef(job);
    job = 0;
    env = 0;
}

void JNICallbakcHelper::onPrepared(int thread_mode) {
    if (thread_mode == THREAD_MAIN) {
        // 主线程
        env->CallVoidMethod(job, jmd_prepared);
    } else if (thread_mode == THREAD_CHILD) {
        // 子线程 env也不可以跨线程吧 对的   全新的env
        JNIEnv * env_child;
        vm->AttachCurrentThread(&env_child, 0);
        env_child->CallVoidMethod(job, jmd_prepared);
        vm->DetachCurrentThread();
    }
}


