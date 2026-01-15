package com.stopbet.app;

import android.content.Context;

public class AdminUnlock {

    private static final String ADM_PIN = "7391";

    public static boolean unlock(Context ctx, String pin) {
        if (!ADM_PIN.equals(pin)) return false;

        EngineState.forceUnlock(ctx);
        return true;
    }
}
