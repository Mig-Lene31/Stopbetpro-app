package com.stopbet.app;

import android.content.Context;

public class MotorGuard {

    public static boolean canRun(Context ctx) {
        return EngineGuard.canUseMotor(ctx);
    }
}
