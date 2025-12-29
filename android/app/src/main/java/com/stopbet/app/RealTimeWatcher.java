package com.stopbet.app;

import android.content.Context;

public class RealTimeWatcher {

    public static boolean check(Context ctx) {

        if (!MotorState.isEnabled(ctx)) return false;
        if (!TimeStore.hasTimeLimit(ctx)) return false;

        if (RealTimeEngine.exceeded(ctx)) {
            MotorState.forceDisable(ctx);
            EngineState.blockFor12Hours(ctx);
            return true;
        }

        return false;
    }
}
