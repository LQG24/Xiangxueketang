#include "DerryPlayer.h"

DerryPlayer::DerryPlayer(const char *data_source, JNICallbakcHelper *helper) {
    // this->data_source = data_source;
    // 如果被释放，会造成悬空指针

    // 深拷贝
    // this->data_source = new char[strlen(data_source)];
    // Java: demo.mp4
    // C层：demo.mp4\0  C层会自动 + \0,  strlen不计算\0的长度，所以我们需要手动加 \0

    this->data_source = new char[strlen(data_source) + 1];
    strcpy(this->data_source, data_source); // 把源 Copy给成员

    this->helper = helper;
}

DerryPlayer::~DerryPlayer() {
    if (data_source) {
        delete data_source;
    }

    if (helper) {
        delete helper;
    }
}

void *task_prepare(void *args) { // 此函数和DerryPlayer这个对象没有关系，你没法拿DerryPlayer的私有成员

    // avformat_open_input(0, this->data_source)

    auto *player = static_cast<DerryPlayer *>(args);
    player->prepare_();

    return 0; // 必须返回，坑，错误很难找
}

void DerryPlayer::prepare_() { // 此函数 是 子线程

    // 为什么FFmpeg源码，大量使用上下文Context？
    // 答：因为FFmpeg源码是纯C的，他不像C++、Java ， 上下文的出现是为了贯彻环境，就相当于Java的this能够操作成员

    /**
     * TODO 第一步：打开媒体地址（文件路径， 直播地址rtmp）
     */
    formatContext = avformat_alloc_context();

    AVDictionary *dictionary = 0;
    av_dict_set(&dictionary, "timeout", "5000000", 0); // 单位微妙

    /**
     * 1，AVFormatContext *
     * 2，路径
     * 3，AVInputFormat *fmt  Mac、Windows 摄像头、麦克风， 我们目前安卓用不到
     * 4，各种设置：例如：Http 连接超时， 打开rtmp的超时  AVDictionary **options
     */
    int r = avformat_open_input(&formatContext, data_source, 0, &dictionary);

    // 释放字典
    av_dict_free(&dictionary);

    if (r) {
        // 把错误信息反馈给Java，回调给Java  Toast【打开媒体格式失败，请检查代码】
        // TODO 第一节课作业：JNI 反射回调到Java方法，并提示
        return;
    }

    /**
     * TODO 第二步：查找媒体中的音视频流的信息
     */
    r = avformat_find_stream_info(formatContext, 0);
    if (r < 0) {
        // TODO 第一节课作业：JNI 反射回调到Java方法，并提示
        return;
    }

    /**
     * TODO 第三步：根据流信息，流的个数，用循环来找
     */
    for (int i = 0; i < formatContext->nb_streams; ++i) {
        /**
         * TODO 第四步：获取媒体流（视频，音频）
         */
        AVStream *stream = formatContext->streams[i];

        /**
         * TODO 第五步：从上面的流中 获取 编码解码的【参数】
         * 由于：后面的编码器 解码器 都需要参数（宽高 等等）
         */
        AVCodecParameters *parameters = stream->codecpar;

        /**
         * TODO 第六步：（根据上面的【参数】）获取编解码器
         */
        AVCodec *codec = avcodec_find_decoder(parameters->codec_id);

        /**
        * TODO 第七步：编解码器 上下文 （这个才是真正干活的）
        */
        AVCodecContext *codecContext = avcodec_alloc_context3(codec);
        if (!codecContext) {
            // TODO 第一节课作业：JNI 反射回调到Java方法，并提示
            return;
        }

        /**
         * TODO 第八步：他目前是一张白纸（parameters copy codecContext）
         */
        r = avcodec_parameters_to_context(codecContext, parameters);
        if (r < 0) {
            // TODO 第一节课作业：JNI 反射回调到Java方法，并提示
            return;
        }

        /**
         * TODO 第九步：打开解码器
         */
        r = avcodec_open2(codecContext, codec, 0);
        if (r) { // 非0就是true
            // TODO 第一节课作业：JNI 反射回调到Java方法，并提示
            return;
        }

        /**
         * TODO 第十步：从编解码器参数中，获取流的类型 codec_type  ===  音频 视频
         */
        if (parameters->codec_type == AVMediaType::AVMEDIA_TYPE_AUDIO) { // 音频
            audio_channel = new AudioChannel();
        } else if (parameters->codec_type == AVMediaType::AVMEDIA_TYPE_VIDEO) { // 视频
            video_channel = new VideoChannel();
        }
    } // for end

    /**
     * TODO 第十一步: 如果流中 没有音频 也没有 视频 【健壮性校验】
     */
    if (!audio_channel && !video_channel) {
        // TODO 第一节课作业：JNI 反射回调到Java方法，并提示
        return;
    }

    /**
     * TODO 第十二步：恭喜你，准备成功，我们的媒体文件 OK了，通知给上层
     */
    if (helper) {
        helper->onPrepared(THREAD_CHILD);
    }
}

void DerryPlayer::prepare() {
    // 问题：当前的prepare函数，是子线程 还是 主线程 ？
    // 答：此函数是被MainActivity的onResume调用下来的（主线程）

    // 解封装 FFmpeg来解析  data_source 可以直接解析吗？
    // 答：data_source == 文件io流，  直播网络rtmp， 所以按道理来说，会耗时，所以必须使用子线程

    // 创建子线程
    pthread_create(&pid_prepare, 0, task_prepare, this);
}




