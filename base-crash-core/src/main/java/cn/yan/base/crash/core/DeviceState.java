package cn.yan.base.crash.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

final class DeviceState {
    public static class Info {
        public String versionName;
        public int versionCode;
        public String osVersion;
        public int osSdkVersion;
        public String manufacturer;
        public String deviceModel;
        public String[] supportedAbis;
        public String networkState;
        public String memoryInfo;
        public String deviceSize;
        public String crashProcessName;
    }

    @NonNull
    static Info get(@NonNull Context context) throws PackageManager.NameNotFoundException {
        Info info = new Info();

        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        info.versionName = pi.versionName;
        info.versionCode = pi.versionCode;
        info.osVersion = Build.VERSION.RELEASE;
        info.osSdkVersion = Build.VERSION.SDK_INT;
        info.manufacturer = Build.MANUFACTURER;
        info.deviceModel = Build.MODEL;
        info.supportedAbis = new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        //TODO ...
        return info;
    }
}
