package com.stopbet.app;

import android.content.Context;

public class MotorOrchestrator {

    public static boolean canStart(Context ctx) {
        return
                DepositStore.hasValue(ctx) &&
                LimitsStore.isConfigured(ctx) &&
                AccessibilityGuard.isEnabled(ctx) &&
                VpnState.isActive(ctx);
    }

    public static void start(Context ctx) {
        if (canStart(ctx)) {
            MotorStateStore.setRunning(ctx, true);
        }
    }

    public static void stop(Context ctx) {
        MotorStateStore.setRunning(ctx, false);
    }
}
