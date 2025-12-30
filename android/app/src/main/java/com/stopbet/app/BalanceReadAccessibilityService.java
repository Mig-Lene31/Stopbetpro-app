package com.stopbet.app;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.Locale;

public class BalanceReadAccessibilityService extends AccessibilityService {

    private long lastReadAt = 0;
    private static final long READ_INTERVAL_MS = 1000;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) return;

        if (!MotorState.isEnabled(this)) return;
        if (EngineState.isBlocked(this)) return;
        if (!EngineGuard.canUseMotor(this)) return;

        long now = System.currentTimeMillis();
        if (now - lastReadAt < READ_INTERVAL_MS) return;
        lastReadAt = now;

        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) return;

        scanNode(root);
    }

    private void scanNode(AccessibilityNodeInfo node) {
        if (node == null) return;

        CharSequence text = node.getText();
        if (text != null) {
            String t = text.toString().toLowerCase(Locale.ROOT);

            if (AnchorRules.containsForbidden(t)) return;

            Float value = AnchorRules.extractValueIfAnchored(t);
            if (value != null) {
                EngineRunner.run(this, value);
            }
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            scanNode(node.getChild(i));
        }
    }

    @Override
    public void onInterrupt() {
        // sem ação
    }
}
