package com.stopbet.app;

import android.content.Context;

public class EngineGuard {

    public static boolean canUseMotor(Context ctx) {

        if (!AppStateAdmin.isReleased(ctx)) return false;

        if (!LicenseState.isValid(ctx)) return false;

        if (EngineState.isBlocked(ctx)) return false;

        if (DailyTimeEngine.exceeded(ctx)) return false;

        return true;
    }
}
