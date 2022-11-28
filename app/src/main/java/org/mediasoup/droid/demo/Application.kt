package org.mediasoup.droid.demo

import android.app.Application
import android.util.Log
import com.tencent.bugly.crashreport.CrashReport
import org.mediasoup.droid.Logger
import org.mediasoup.droid.MediasoupClient

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        //https://bugly.qq.com/v2/product/apps/dc14197508?pid=1
        CrashReport.initCrashReport(getApplicationContext(), "dc14197508", false)
        Log.d("CrashReport", "v:"+CrashReport.getAppVer())//3.0.8-beta-3-DEBUG
        Logger.setLogLevel(Logger.LogLevel.LOG_DEBUG)
        Logger.setDefaultHandler()
        MediasoupClient.initialize(applicationContext)
    }
}