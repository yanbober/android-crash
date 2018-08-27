package cn.yan.android.crash

import android.app.Application
import cn.yan.crash.core.NativeCrash

class DemoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NativeCrash.init(this)
    }
}