package com.stopbet.app.androidnative

import com.facebook.react.bridge.*
import com.facebook.react.modules.core.DeviceEventManagerModule

class StopBetModule(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    companion object {
        lateinit var instance: StopBetModule
    }

    init {
        instance = this
    }

    override fun getName(): String {
        return "StopBetBridge"
    }

    fun sendBalance(balance: Double) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit("STOPBET_BALANCE", balance)
    }
}
