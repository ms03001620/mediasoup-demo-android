package org.mediasoup.droid.lib

import android.content.Context
import org.mediasoup.droid.Logger
import org.webrtc.AudioTrack
import org.webrtc.CameraVideoCapturer.CameraSwitchHandler
import org.webrtc.VideoTrack

class LocalDeviceHelper {
    lateinit var peerConnectionUtils: PeerConnectionUtils

    var localVideoTrack: VideoTrack? = null
    var localAudioTrack: AudioTrack? = null

    fun start() {
        peerConnectionUtils = PeerConnectionUtils()
    }

    fun switchCam(switchHandler: CameraSwitchHandler) {
        peerConnectionUtils.switchCam(switchHandler)
    }

    fun enableMicImpl(context: Context) {
        localAudioTrack = peerConnectionUtils.createAudioTrack(context, "mic")
        localAudioTrack?.setEnabled(true)
    }

    fun enableCamImpl(context: Context) {
        localVideoTrack = peerConnectionUtils.createVideoTrack(context, "cam")
        localVideoTrack?.setEnabled(true)
    }

    fun getAudioTrack(): AudioTrack {
        return localAudioTrack ?: throw IllegalStateException("need call enableMicImpl first")
    }

    fun getVideoTrack(): VideoTrack {
        return localVideoTrack ?: throw IllegalStateException("need call enableCamImpl first")
    }

    fun setLocalVideoTrackEnable(enable: Boolean){
        localVideoTrack?.setEnabled(enable)
    }

    fun setLocalAudioTrackEnable(enable: Boolean){
        localAudioTrack?.setEnabled(enable)
    }

    fun closeVideo() {
        localVideoTrack?.setEnabled(false)
        localVideoTrack?.dispose()
        localVideoTrack = null
    }

    fun closeAudio() {
        localAudioTrack?.setEnabled(false)
        localAudioTrack?.dispose()
        localAudioTrack = null
    }

    fun dispose() {
        peerConnectionUtils.dispose()
    }


}