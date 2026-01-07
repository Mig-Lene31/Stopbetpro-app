package com.stopbet.app;

import android.content.Context;

public class EngineGuard {

    public static boolean canUseMotor(Context ctx) {
        if (!MotorState.isEnabled(ctx)) return false;
        if (EngineState.isBlocked(ctx)) return false;
        if (DailyTimeEngine.exceeded(ctx)) return false;
        return true;
    }

    public static boolean canRun(Context ctx) {
        return canUseMotor(ctx);
    }
}
