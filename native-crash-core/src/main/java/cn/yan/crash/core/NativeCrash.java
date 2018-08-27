package cn.yan.crash.core;

import android.content.Context;
import android.support.annotation.NonNull;
import java.io.File;
import cn.yan.base.crash.core.CrashDump;
import cn.yan.base.crash.core.DebugLog;

public class NativeCrash {
    private static volatile boolean isInited = false;

    public static synchronized void init(@NonNull Context context) {
        if (isInited) {
            DebugLog.w("Native Crash has already init.");
            return;
        }

        File dumpDir = CrashDump.getAssignedDir(context, CrashDump.DIR_NATIVE);
        if (dumpDir != null) {
            NativeHandler.init(dumpDir.getAbsolutePath());
            isInited = true;
        } else {
            DebugLog.w("Native Crash dump assigned directory is null.");
        }
    }

    public static void crash() {
        NativeHandler.crash();
    }
}
