package cn.yan.base.crash.core;

import android.util.Log;

public class DebugLog {
    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "androidCrash";

    private DebugLog() {}

    public static void d(String desc) {
        if (DEBUG) {
            Log.d(TAG, desc);
        }
    }

    public static void d(String desc, Throwable tr) {
        if (DEBUG) {
            Log.d(TAG, desc, tr);
        }
    }

    public static void v(String desc) {
        if (DEBUG) {
            Log.v(TAG, desc);
        }
    }
    public static void v(String desc, Throwable tr) {
        if (DEBUG) {
            Log.v(TAG, desc, tr);
        }
    }

    public static void w(String desc) {
        if (DEBUG) {
            Log.w(TAG, desc);
        }
    }

    public static void w(String desc, Throwable tr) {
        if (DEBUG) {
            Log.w(TAG, desc, tr);
        }
    }

    public static void i(String desc) {
        if (DEBUG) {
            Log.i(TAG, desc);
        }
    }

    public static void i(String desc, Throwable tr) {
        if (DEBUG) {
            Log.i(TAG, desc, tr);
        }
    }

    public static void e(String desc) {
        if (DEBUG) {
            Log.e(TAG, desc);
        }
    }

    public static void e(String desc, Throwable tr) {
        if (DEBUG) {
            Log.e(TAG, desc, tr);
        }
    }
}
