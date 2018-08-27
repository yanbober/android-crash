#include <android/log.h>

#ifndef ANDROID_CRASH_LOGCAT_H
#define ANDROID_CRASH_LOGCAT_H

#ifdef __cplusplus
extern "C" {
#endif

#define LOGCAT_DEBUG 1
#define TAG "androidCrash"

#if LOGCAT_DEBUG
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__)
#else
#define LOGD(...) NULL
#define LOGI(...) NULL
#define LOGW(...) NULL
#define LOGE(...) NULL
#define LOGF(...) NULL
#endif

#ifdef __cplusplus
}
#endif

#endif //ANDROID_CRASH_LOGCAT_H
