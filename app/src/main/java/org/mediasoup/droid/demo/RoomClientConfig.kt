package org.mediasoup.droid.demo

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import org.mediasoup.droid.lib.RoomOptions
import org.mediasoup.droid.lib.Utils

class RoomClientConfig {
    lateinit var data:  RoomClientConfigData
    lateinit var roomOptions: RoomOptions

    fun loadFromShare(context: Context) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        loadConfigData(preferences)
        loadRoomOption(preferences)
    }

    private fun loadRoomOption(preferences: SharedPreferences) {
        roomOptions = RoomOptions()
        roomOptions.setProduce(preferences.getBoolean("produce", true));
        roomOptions.setConsume(preferences.getBoolean("consume", true));
        roomOptions.setForceTcp(preferences.getBoolean("forceTcp", false));
    }

    private fun loadConfigData(preferences: SharedPreferences) {
        // Room initial config.
        var mRoomId = preferences.getString("roomId", "")
        var mPeerId = preferences.getString("peerId", "")
        var mDisplayName = preferences.getString("displayName", "")
        var mForceH264 = preferences.getBoolean("forceH264", false)
        var mForceVP9 = preferences.getBoolean("forceVP9", false)
        if (TextUtils.isEmpty(mRoomId)) {
            mRoomId = Utils.getRandomString(8)
            preferences.edit().putString("roomId", mRoomId).apply()
        }
        if (TextUtils.isEmpty(mPeerId)) {
            mPeerId = Utils.getRandomString(8)
            preferences.edit().putString("peerId", mPeerId).apply()
        }
        if (TextUtils.isEmpty(mDisplayName)) {
            mDisplayName = Utils.getRandomString(8)
            preferences.edit().putString("displayName", mDisplayName).apply()
        }

        data = RoomClientConfigData(mRoomId, mPeerId, mDisplayName, mForceH264, mForceVP9)
    }

}