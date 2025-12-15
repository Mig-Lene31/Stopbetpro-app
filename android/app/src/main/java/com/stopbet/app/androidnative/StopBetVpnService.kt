package com.stopbet.app.androidnative

import android.net.VpnService
import android.content.Intent
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetSocketAddress
import java.util.concurrent.Executors

class StopBetVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null
    private val blockedDomains = mutableSetOf<String>()
    private val executor = Executors.newSingleThreadExecutor()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        loadBlockedDomains()
        startVpn()
        startDnsInterceptor()
        return START_STICKY
    }

    private fun loadBlockedDomains() {
        try {
            val reader = BufferedReader(InputStreamReader(assets.open("blocked_domains.txt")))
            reader.forEachLine {
                val d = it.trim()
                if (d.isNotEmpty() && !d.startsWith("#")) {
                    blockedDomains.add(d)
                }
            }
            reader.close()
            Log.i("STOPBET", "Domínios bloqueados: ${blockedDomains.size}")
        } catch (e: Exception) {
            Log.e("STOPBET", "Erro ao carregar domínios", e)
        }
    }

    private fun startVpn() {
        if (vpnInterface != null) return

        val builder = Builder()
        builder.setSession("StopBetPro")
        builder.addAddress("10.0.0.2", 32)
        builder.addRoute("0.0.0.0", 0)
        builder.addDnsServer("10.0.0.1")

        vpnInterface = builder.establish()
        Log.i("STOPBET", "VPN iniciada")
    }

    private fun startDnsInterceptor() {
        executor.execute {
            try {
                val socket = DatagramSocket(null)
                socket.reuseAddress = true
                socket.bind(InetSocketAddress("127.0.0.1", 53))

                val buffer = ByteArray(512)

                while (true) {
                    val packet = DatagramPacket(buffer, buffer.size)
                    socket.receive(packet)

                    val domain = DnsBlocker.extractDomainFromDnsQuery(packet.data)

                    if (domain != null && DnsBlocker.shouldBlock(domain, blockedDomains)) {
                        Log.w("STOPBET", "DNS BLOQUEADO: $domain")
                        continue
                    }
                }
            } catch (e: Exception) {
                Log.e("STOPBET", "Erro DNS", e)
            }
        }
    }

    override fun onDestroy() {
        vpnInterface?.close()
        vpnInterface = null
        super.onDestroy()
    }
}
