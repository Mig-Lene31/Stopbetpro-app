package com.stopbet.app;

import android.content.Context;

public class TimeEngine {

    public static void tick(Context ctx) {

        if (!MotorState.isEnabled(ctx)) return;
        if (!TimeStore.hasTimeLimit(ctx)) return;

        DailyTimeEngine.addMinute(ctx);

        if (DailyTimeEngine.exceeded(ctx)) {
            MotorState.forceDisable(ctx);
            EngineState.blockFor12Hours(ctx);
        }
    }
}
