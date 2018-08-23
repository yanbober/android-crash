ROOT_PATH := $(call my-dir)
include $(ROOT_PATH)/third-breakpad/android/google_breakpad/Android.mk
LOCAL_PATH := $(ROOT_PATH)
include $(CLEAR_VARS)
LOCAL_MODULE := native-crash
LOCAL_SRC_FILES := NativeHandler.cpp
LOCAL_STATIC_LIBRARIES += breakpad_client

include $(BUILD_SHARED_LIBRARY)
