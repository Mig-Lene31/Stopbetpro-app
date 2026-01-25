package com.stopbet.app;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class BalanceReadAccessibilityService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.notificationTimeout = 200;
        setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (!MotorStateStore.isRunning(this)) return;

        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) return;

        scanNode(root);
    }

    private void scanNode(AccessibilityNodeInfo node) {
        if (node.getText() != null) {
            Double value = MoneyTextParser.extract(node.getText().toString());
            if (value != null && value > 0) {
                BalanceSnapshotStore.save(this, value);
            }
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) scanNode(child);
        }
    }

    @Override
    public void onInterrupt() {}
}
