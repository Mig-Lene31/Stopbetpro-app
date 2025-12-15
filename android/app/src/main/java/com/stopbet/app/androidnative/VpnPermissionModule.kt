package com.stopbet.app.androidnative

import android.app.Activity
import android.content.Intent
import android.net.VpnService
import com.facebook.react.bridge.*

class VpnPermissionModule(
    private val reactContext: ReactApplicationContext
) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "VpnPermission"
    }

    @ReactMethod
    fun requestPermission() {
        val intent = VpnService.prepare(reactContext)
        if (intent != null) {
            currentActivity?.startActivityForResult(intent, 1001)
        } else {
            startVpn()
        }
    }

    private fun startVpn() {
        val intent = Intent(reactContext, StopBetVpnService::class.java)
        reactContext.startService(intent)
    }
}
