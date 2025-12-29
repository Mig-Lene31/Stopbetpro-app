package com.stopbet.app;

import android.content.Context;

public class TimeEngine {

    public static void tick(Context ctx) {
        if (!MotorState.isEnabled(ctx)) return;

        // registra 1 minuto simbólico por ação
        DailyTimeEngine.addMinute(ctx);

        if (DailyTimeEngine.exceeded(ctx)) {
            EngineState.blockFor12Hours(ctx);
        }
    }
}
