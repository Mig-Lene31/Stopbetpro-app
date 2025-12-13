package com.stopbet.app.androidnative

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.content.Intent
import android.util.Log
import com.stopbet.app.BlockActivity

class ProAccessibilityService : AccessibilityService() {

    private var lastValue: Double? = null
    private var stableReads = 0

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val root = rootInActiveWindow ?: return
        scan(root)
    }

    private fun scan(node: AccessibilityNodeInfo) {
        val text = node.text?.toString() ?: ""
        val value = extractValue(text)

        if (value != null) validate(value)

        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { scan(it) }
        }
    }

    private fun extractValue(text: String): Double? {
        val clean = text.replace(".", "").replace(",", ".")
        val regex = Regex("(\\d+\\.\\d+)")
        return regex.find(clean)?.value?.toDoubleOrNull()
    }

    private fun validate(value: Double) {
        if (lastValue == value) {
            stableReads++
        } else {
            lastValue = value
            stableReads = 1
        }

        if (stableReads >= 3) {
            onConfirmedBalance(value)
        }
    }

    private fun onConfirmedBalance(balance: Double) {
        Log.d("STOPBET_PRO", "Saldo confirmado: $balance")
        StopBetModule.instance.sendBalance(balance)
    }

    override fun onInterrupt() {}
}
