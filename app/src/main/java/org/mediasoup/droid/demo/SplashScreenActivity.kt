package org.mediasoup.droid.demo

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.mediasoup.droid.demo.R
import android.widget.TextView
import android.content.Intent
import android.view.View
import org.mediasoup.droid.demo.RoomActivity
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initVersion()
        checkPermission()
    }

    private fun initVersion() {
        val tvVer = findViewById<View>(R.id.text_ver) as TextView
        tvVer.text =
            BuildConfig.VERSION_NAME + "-" + BuildConfig.VERSION_CODE
    }

    private fun enterMain() {
        findViewById<View>(R.id.mediasoup).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, RoomActivity::class.java))
            finish()
        }, 1500)
    }

    private val permissionHandler: PermissionHandler = object : PermissionHandler() {
        override fun onGranted() {
            enterMain()
        }
    }

    private fun checkPermission() {
        val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val rationale = "Please provide permissions"
        val options =
            Permissions.Options().setRationaleDialogTitle("Info").setSettingsDialogTitle("Warning")
        Permissions.check(this, permissions, rationale, options, permissionHandler)
    }
}