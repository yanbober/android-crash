package cn.yan.base.crash.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.io.File;
import static java.lang.annotation.RetentionPolicy.SOURCE;

final public class CrashDump {
    @Retention(SOURCE)
    @StringDef({DIR_NATIVE, DIR_JVM})
    public @interface CrashType {}

    public static final String DIR_NATIVE = "native";
    public static final String DIR_JVM = "jvm";

    private static final String DIR_CRASH = ".crash";

    @Nullable
    public static File getAssignedDir(@NonNull Context context, @CrashType String type) {
        File file = context.getExternalCacheDir();
        if (file != null && file.exists()) {
            String typeDir = file.getPath() + File.separator + DIR_CRASH + type;
            file = new File(typeDir);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return file;
    }
}
