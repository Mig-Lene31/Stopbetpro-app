package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static void process(Context ctx, float saldo) {

        if (!MotorState.isEnabled(ctx)) return;

        float win = LimitsState.getWin(ctx);
        float loss = LimitsState.getLoss(ctx);

        if ((win > 0 && saldo >= win) || (loss < 0 && saldo <= loss)) {
            MotorState.forceDisable(ctx);
            EngineState.blockFor12Hours(ctx);
        }
    }
}
