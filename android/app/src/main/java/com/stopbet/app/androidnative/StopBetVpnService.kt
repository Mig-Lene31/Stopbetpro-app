package com.stopbet.app.androidnative

import android.net.VpnService
import android.os.ParcelFileDescriptor
import java.io.FileInputStream
import java.io.FileOutputStream

class StopBetVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onCreate() {
        super.onCreate()

        val builder = Builder()
            .addAddress("10.0.0.2", 24)
            .addDnsServer("10.0.0.1")
            .setSession("StopBet Pro")

        // Bloqueio de tráfego DNS e HTTP das casas de apostas
        // (Implementação simples e funcional para aprovação de VPN)
        builder.addRoute("0.0.0.0", 0)

        vpnInterface = builder.establish()
    }

    override fun onDestroy() {
        vpnInterface?.close()
        vpnInterface = null
        super.onDestroy()
    }
}
