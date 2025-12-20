package com.stopbet.app.androidnative

import android.content.Intent
import android.net.VpnService
import com.facebook.react.bridge.*

class VpnPermissionModule(private val reactContext: ReactApplicationContext)
    : ReactContextBaseJavaModule(reactContext) {

    override fun getName() = "VpnPermissionModule"

    @ReactMethod
    fun requestPermission(promise: Promise) {
        val intent = VpnService.prepare(reactContext)

        if (intent != null) {
            currentActivity?.startActivityForResult(intent, 1000)
            promise.resolve(false)
        } else {
            promise.resolve(true) // já tinha permissão
        }
    }
}
