package cn.yan.base.crash.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.text.format.Formatter;
import java.util.List;

final class DeviceAppState {
    public static class Info {
        /**
         * App version name.
         */
        public String versionName;

        /**
         * App version code.
         */
        public int versionCode;

        /**
         * The ROM OS version.
         */
        public String osVersion;

        /**
         * The ROM OS Android SDK Level.
         */
        public int osSdkVersion;

        /**
         * The manufacturer of the product/hardware.
         */
        public String manufacturer;

        /**
         * The end-user-visible name for the end product.
         */
        public String deviceModel;

        /**
         * The name of the instruction set (CPU type + ABI convention) of native code.
         * The name of the second instruction set (CPU type + ABI convention) of native code.
         */
        public String[] supportedAbis;

        /**
         * Current network type of the OS.
         */
        public String networkType;

        /**
         * Current running OS available memory.
         */
        public String osAvailMemory;

        /**
         * Current running OS is in the low memory state.
         */
        public boolean osIsLowMemory;

        /**
         * Current app max memory, defined by JVM config.
         */
        public String appMaxMemory;

        /**
         * Current running app can use memory.
         */
        public String appCanUseMemory;

        /**
         * Current running app free state memory.
         */
        public String appFreeMemory;

        /**
         * Current running app already used memory.
         */
        public String appUsedMemory;

        /**
         * Current running app crashing in which process, maybe the app have multi process.
         */
        public String crashProcessName;

        /**
         * Current running app channel.
         */
        public String channel;
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    @NonNull
    static Info getMixInfo(@NonNull Context context, @NonNull String channel) throws PackageManager.NameNotFoundException {
        Info info = new Info();

        PackageManager manager = context.getPackageManager();
        PackageInfo pmPackageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        info.versionName = pmPackageInfo.versionName;
        info.versionCode = pmPackageInfo.versionCode;
        info.osVersion = Build.VERSION.RELEASE;
        info.osSdkVersion = Build.VERSION.SDK_INT;
        info.manufacturer = Build.MANUFACTURER;
        info.deviceModel = Build.MODEL;
        info.supportedAbis = new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        info.networkType = NetState.getNetworkType(context);
        info.crashProcessName = getProcessName(context, Process.myPid());

        ActivityManager.MemoryInfo memoryInfo = getOsMemoryInfo(context);
        if (memoryInfo != null) {
            info.osAvailMemory = Formatter.formatFileSize(context, memoryInfo.availMem);
            info.osIsLowMemory = memoryInfo.lowMemory;
        }

        Runtime runtime = Runtime.getRuntime();
        if (runtime != null) {
            info.appMaxMemory = Formatter.formatFileSize(context, runtime.maxMemory());
            info.appCanUseMemory = Formatter.formatFileSize(context, runtime.totalMemory());
            info.appFreeMemory = Formatter.formatFileSize(context, runtime.freeMemory());
            info.appUsedMemory = Formatter.formatFileSize(context, runtime.totalMemory() - runtime.freeMemory());
        }

        info.channel = channel;
        return info;
    }

    @Nullable
    public static String getProcessName(@NonNull Context context, int pid) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) {
            return null;
        }
        List<ActivityManager.RunningAppProcessInfo> runningApps = manager.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    @Nullable
    public static ActivityManager.MemoryInfo getOsMemoryInfo(@NonNull Context context){
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) {
            return null;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo() ;
        manager.getMemoryInfo(memoryInfo) ;
        return memoryInfo;
    }
}
