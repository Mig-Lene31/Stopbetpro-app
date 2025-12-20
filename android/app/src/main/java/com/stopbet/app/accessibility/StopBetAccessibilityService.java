package com.stopbet.app.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

import com.stopbet.app.blocker.BlockController;

public class StopBetAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (BlockController.isBlocked()) {
            BlockController.forceBlock(this, "BLOCK_ACTIVE");
        }
    }

    @Override
    public void onInterrupt() {}
}
