#include "cn_yan_crash_core_NativeHandler.h"
#include "logcat.h"
#include "client/linux/handler/exception_handler.h"
#include "client/linux/handler/minidump_descriptor.h"

namespace {
    bool DumpCallback(const google_breakpad::MinidumpDescriptor& descriptor,
                      void* context,
                      bool succeeded) {
        LOGD("Dump path: %s.\n", descriptor.path());
        return succeeded;
    }
}

JNIEXPORT void JNICALL Java_cn_yan_crash_core_NativeHandler_init
            (JNIEnv* jniEnv, jobject obj, jstring path) {
    const char* filePath = jniEnv->GetStringUTFChars(path, JNI_FALSE);
    LOGD("Init file path is:%s.\n", filePath);
    google_breakpad::MinidumpDescriptor descriptor(filePath);
    google_breakpad::ExceptionHandler eh(descriptor, NULL, DumpCallback,
                                         NULL, true, -1);
    jniEnv->ReleaseStringUTFChars(path, filePath);
    LOGD("Init done.\n");
}

JNIEXPORT void JNICALL Java_cn_yan_crash_core_NativeHandler_crash
        (JNIEnv* jniEnv, jobject obj) {
    LOGD("Start crash....\n");
    volatile int* a = reinterpret_cast<volatile int*>(NULL);
    *a = 1;
    LOGD("Crashed....\n");
}