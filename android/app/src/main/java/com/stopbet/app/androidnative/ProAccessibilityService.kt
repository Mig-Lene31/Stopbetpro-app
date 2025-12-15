package com.stopbet.app.androidnative

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import android.util.Log

class ProAccessibilityService : AccessibilityService() {

    private val blockedApps = listOf(
        "com.bet365",
        "com.sportingbet",
        "com.betano",
        "com.pixbet",
        "com.betfair"
    )

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return

        val packageName = event.packageName?.toString() ?: return

        if (blockedApps.contains(packageName)) {
            Log.d("STOPBET", "App bloqueado detectado: $packageName")

            val intent = Intent(this, BlockedActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onInterrupt() {}
}
