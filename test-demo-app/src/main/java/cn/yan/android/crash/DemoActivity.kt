package cn.yan.android.crash

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import cn.yan.crash.core.NativeCrash

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        val permissions = arrayOf("android.permission.READ_EXTERNAL_STORAGE",
                                    "android.permission.WRITE_EXTERNAL_STORAGE")
        permissions.forEach {
            val grant = ActivityCompat.checkSelfPermission(this, it)
            if (grant != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(it), 1)
            } else {
                NativeCrash.init(this.applicationContext)
            }
        }
    }
}
