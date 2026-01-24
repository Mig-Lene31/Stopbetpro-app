package com.stopbet.app;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class KairosAccessibilityService extends AccessibilityService {

    private static final String TAG = "KAIROS_ACCESS";

    @Override
    protected void onServiceConnected() {
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.notificationTimeout = 100;
        setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null || event.getPackageName() == null) return;

        if (!BetInterventionPolicy.shouldIntervene(this)) return;

        String pkg = event.getPackageName().toString();

        if (BetDetectionEngine.isBetPackage(pkg)) {
            Log.w(TAG, "INTERVENÇÃO AUTORIZADA (app): " + pkg);
        }
    }

    @Override
    public void onInterrupt() {}
}
