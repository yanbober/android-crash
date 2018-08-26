package cn.yan.crash.core;

import android.content.Context;
import android.support.annotation.NonNull;
import java.io.File;
import cn.yan.base.crash.core.CrashDump;
import cn.yan.base.crash.core.DebugLog;

public class NativeCrash {
    public static void init(@NonNull Context context) {
        File dumpDir = CrashDump.getAssignedDir(context, CrashDump.DIR_NATIVE);
        if (dumpDir != null) {
            NativeHandler.init(dumpDir.getAbsolutePath());
        } else {
            DebugLog.w("Native Crash dump assigned directory is null.");
        }
    }
}
