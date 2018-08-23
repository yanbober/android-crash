package cn.yan.crash.core;

public class NativeHandler {
    static {
        System.loadLibrary("native-crash");
    }

    public native void init(String path);
}