package com.stopbet.app;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class KairosAccessibilityService extends AccessibilityService {

    private static final String TAG = "KAIROS_ACCESS";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) return;

        CharSequence pkg = event.getPackageName();
        if (pkg != null) {
            Log.d(TAG, "APP DETECTADO: " + pkg.toString());
        }
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "Accessibility interrompido");
    }

    @Override
    protected void onServiceConnected() {
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes =
                AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED |
                AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED;

        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.notificationTimeout = 100;

        setServiceInfo(info);

        Log.d(TAG, "Kairos Accessibility ATIVO");
    }
}
