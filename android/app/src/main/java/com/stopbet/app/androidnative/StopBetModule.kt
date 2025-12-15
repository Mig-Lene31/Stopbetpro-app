package com.stopbet.app.androidnative

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class StopBetModule(
    reactContext: ReactApplicationContext
) : ReactContextBaseJavaModule(reactContext) {

    init {
        BalanceBridge.init(reactContext)
    }

    override fun getName(): String {
        return "StopBetModule"
    }

    @ReactMethod
    fun ping() {
        // Apenas para garantir inicialização do módulo
    }
}
