package com.stopbet.app.androidnative

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.util.Log
import java.util.regex.Pattern

class BlockerAccessibilityService : AccessibilityService() {

    private val balancePattern =
        Pattern.compile("(R\\$\\s*\\d+[\\.,]?\\d*)")

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        val source = event.source ?: return
        scanNode(source)
    }

    private fun scanNode(node: AccessibilityNodeInfo) {
        val text = node.text?.toString() ?: ""

        if (
            text.contains("R$") ||
            text.contains("Saldo") ||
            text.contains("Balance")
        ) {
            extractBalance(text)
        }

        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { scanNode(it) }
        }
    }

    private fun extractBalance(text: String) {
        val matcher = balancePattern.matcher(text)

        if (matcher.find()) {
            val raw = matcher.group(1)
                .replace("R$", "")
                .replace(" ", "")
                .replace(",", ".")
                .trim()

            try {
                val value = raw.toDouble()
                sendBalanceToJS(value)
            } catch (e: Exception) {
                Log.e("STOPBET", "Erro ao converter saldo: $raw")
            }
        }
    }

    private fun sendBalanceToJS(value: Double) {
        BalanceBridge.send(value)
    }

    override fun onInterrupt() {}
}
