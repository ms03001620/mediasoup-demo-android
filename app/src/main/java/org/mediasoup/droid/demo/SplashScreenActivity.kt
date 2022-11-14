package org.mediasoup.droid.demo

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import org.mediasoup.droid.lib.RoomClient
import org.mediasoup.droid.lib.UrlFactory
import org.mediasoup.droid.lib.lv.RoomStore

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initVersion()
        checkPermission()
        //loadTestEnv
    }

    private fun initVersion() {
        val tvVer = findViewById<View>(R.id.text_ver) as TextView
        tvVer.text =
            BuildConfig.VERSION_NAME + "-" + BuildConfig.VERSION_CODE + "-" + UrlFactory.HOSTNAME
    }

    private fun enterMain() {
        findViewById<View>(R.id.mediasoup).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, RoomActivity::class.java))
            finish()
        }, 1500)
    }

    private fun loadTestEnv() {
        var mRoomStore: RoomStore? = null
        var mRoomClient: RoomClient? = null
        var roomClientConfig = RoomClientConfig()


        roomClientConfig.loadFromShare(applicationContext)
        roomClientConfig.print()

        //initCamera();
        mRoomStore = RoomStore()
        mRoomClient =
            RoomClient(this, mRoomStore, roomClientConfig.data, roomClientConfig.roomOptions)

        findViewById<View>(R.id.text_ver).setOnClickListener {
            mRoomClient?.join()
        }
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