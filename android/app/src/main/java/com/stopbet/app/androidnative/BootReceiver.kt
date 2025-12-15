package com.stopbet.app.androidnative

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            Log.d("STOPBET", "Reiniciando VPN após reinício do dispositivo.")
            val vpnIntent = Intent(context, StopBetVpnService::class.java)
            context.startService(vpnIntent)
        }
    }
}
