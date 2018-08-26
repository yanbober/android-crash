package cn.yan.crash.core;

final class NativeHandler {
    static {
        System.loadLibrary("native-crash");
    }

    public native void init(String path);
}