package com.stopbet.app;

import android.content.Context;

public class EngineGuard {

    public static boolean canUseMotor(Context ctx) {
        return EngineRuntime.isRunning(ctx) && !EngineRuntime.isBlocked(ctx);
    }
}
