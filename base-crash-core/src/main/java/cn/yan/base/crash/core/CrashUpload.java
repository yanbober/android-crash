package cn.yan.base.crash.core;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

final public class CrashUpload {
    public static void start(@NonNull Context context) {
        Intent intent = new Intent(context, WorkService.class);
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    public static final class WorkService extends IntentService {
        private static final String TAG = "CrashUpload$WorkService";

        public WorkService() {
            super(TAG);
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            //TODO ...
            Log.i(TAG, "WorkService------------onHandleIntent.");
        }
    }
}
