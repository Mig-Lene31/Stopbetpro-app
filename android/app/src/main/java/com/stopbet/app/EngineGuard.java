package com.stopbet.app;

import android.content.Context;

public class EngineGuard {

    public static boolean canRun(Context ctx) {
        if (!MotorStateStore.isEnabled(ctx)) return false;
        if (EngineState.isBlocked(ctx)) return false;
        return true;
    }

    // Compatibilidade com chamadas antigas
    public static boolean canUseMotor(Context ctx) {
        return canRun(ctx);
    }
}
