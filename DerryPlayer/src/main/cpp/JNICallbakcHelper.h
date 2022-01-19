#ifndef DERRYPLAYER_JNICALLBAKCHELPER_H
#define DERRYPLAYER_JNICALLBAKCHELPER_H

#include <jni.h>
#include "util.h"

class JNICallbakcHelper {

private:
    JavaVM *vm = 0;
    JNIEnv *env = 0;
    jobject job;
    jmethodID jmd_prepared;

public:
    JNICallbakcHelper(JavaVM *vm, JNIEnv *env, jobject job);

    virtual ~JNICallbakcHelper();

    void onPrepared(int thread_mode);
};


#endif //DERRYPLAYER_JNICALLBAKCHELPER_H
