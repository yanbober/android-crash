package cn.yan.crash.core;

final class NativeHandler {
    static {
        System.loadLibrary("native-crash");
    }

    public static native void init(String path);
}