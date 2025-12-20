package com.stopbet.app.androidnative

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {

            // Reinicia o serviço VPN
            val vpn = Intent(context, StopBetVpnService::class.java)
            context.startService(vpn)

            // Acessibilidade o usuário ativa manualmente (segurança do Android)
        }
    }
}
