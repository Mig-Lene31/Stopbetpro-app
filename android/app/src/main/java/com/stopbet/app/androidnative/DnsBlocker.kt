package com.stopbet.app.androidnative

import java.nio.ByteBuffer
import java.util.Locale

object DnsBlocker {

    fun shouldBlock(domain: String, blocked: Set<String>): Boolean {
        val d = domain.lowercase(Locale.getDefault())
        return blocked.any { d == it || d.endsWith(".$it") }
    }

    fun extractDomainFromDnsQuery(packet: ByteArray): String? {
        return try {
            val buffer = ByteBuffer.wrap(packet)
            buffer.position(12)

            val domain = StringBuilder()
            while (true) {
                val length = buffer.get().toInt()
                if (length <= 0) break

                val label = ByteArray(length)
                buffer.get(label)
                domain.append(String(label)).append(".")
            }
            domain.toString().dropLast(1)
        } catch (e: Exception) {
            null
        }
    }
}
