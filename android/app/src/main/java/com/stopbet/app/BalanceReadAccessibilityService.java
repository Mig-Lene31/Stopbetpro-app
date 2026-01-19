package com.stopbet.app;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class BalanceReadAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (!MotorStateStore.isRunning(this)) return;
        if (EngineState.isBlocked(this)) return;

        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) return;

        Double balance = BalanceParser.extract(root);
        if (balance == null) return;

        StopLimitChecker.check(this, balance);
    }

    @Override
    public void onInterrupt() {}
}
