package com.stopbet.app;

import android.content.Context;

public class TimeEngine {

    public static void tick(Context ctx) {
        if (!MotorState.isEnabled(ctx)) return;
        if (DailyTimeEngine.exceeded(ctx)) {
            EngineState.block(ctx);
        }
    }
}
