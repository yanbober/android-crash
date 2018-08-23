#include "cn_yan_crash_core_NativeHandler.h"
#include "client/linux/handler/exception_handler.h"
#include "client/linux/handler/minidump_descriptor.h"

#define DEBUG 1

namespace {
    bool DumpCallback(const google_breakpad::MinidumpDescriptor& descriptor,
                      void* context,
                      bool succeeded) {
        printf("Dump path: %s\n", descriptor.path());
        return succeeded;
    }

#if(DEBUG)
    void Crash() {
        volatile int* a = reinterpret_cast<volatile int*>(NULL);
        *a = 1;
    }
#endif
}
JNIEXPORT void JNICALL Java_cn_yan_crash_core_NativeHandler_init
            (JNIEnv* jniEnv, jobject obj, jstring path) {
    const char* filePath = jniEnv->GetStringUTFChars(path, JNI_FALSE);
    google_breakpad::MinidumpDescriptor descriptor(filePath);
    google_breakpad::ExceptionHandler eh(descriptor, NULL, DumpCallback,
                                         NULL, true, -1);
    jniEnv->ReleaseStringUTFChars(path, filePath);

#if(DEBUG)
    Crash();
#endif
}