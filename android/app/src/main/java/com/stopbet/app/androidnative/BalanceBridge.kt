package com.stopbet.app.androidnative

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.modules.core.DeviceEventManagerModule

object BalanceBridge {

    private var reactContext: ReactApplicationContext? = null

    fun init(context: ReactApplicationContext) {
        reactContext = context
    }

    fun send(balance: Double) {
        reactContext
            ?.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            ?.emit("BALANCE_DETECTED", balance)
    }
}
