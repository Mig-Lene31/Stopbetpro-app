package com.stopbet.app;

import android.content.Context;

public class BetInterventionPolicy {

    public static boolean shouldIntervene(Context ctx) {

        if (!MotorStateStore.isRunning(ctx)) return false;

        if (!DepositStore.hasValue(ctx)) return false;

        if (!LimitsStore.hasLimits(ctx)) return false;

        if (!AccessibilityGuard.isEnabled(ctx)) return false;

        if (!VpnState.isActive(ctx)) return false;

        return true;
    }
}
