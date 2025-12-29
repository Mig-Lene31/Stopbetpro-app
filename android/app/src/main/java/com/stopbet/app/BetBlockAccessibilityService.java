package com.stopbet.app;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.content.Intent;

import java.util.List;

public class BetBlockAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (!MotorState.isEnabled(this)) return;
        if (!EngineState.isBlocked(this)) {

            AccessibilityNodeInfo root = getRootInActiveWindow();
            if (root == null) return;

            for (String domain : BetSites.LIST) {
                if (containsText(root, domain)) {
                    EngineState.blockFor12Hours(this);
                    MotorState.forceDisable(this);

                    Intent i = new Intent(this, BlockedActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    break;
                }
            }
        }
    }

    private boolean containsText(AccessibilityNodeInfo node, String text) {
        if (node.getText() != null &&
            node.getText().toString().toLowerCase().contains(text)) {
            return true;
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null && containsText(child, text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onInterrupt() {
        // nada
    }
}
