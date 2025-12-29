package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static boolean process(Context ctx, float saldo) {
        if (!MotorState.isEnabled(ctx)) return false;

        float win = LimitsStore.getWin(ctx);
        float loss = LimitsStore.getLoss(ctx);

        if ((win > 0 && saldo >= win) || (loss < 0 && saldo <= loss)) {
            EngineState.blockFor12Hours(ctx);
            MotorState.forceDisable(ctx);
            return true;
        }
        return false;
    }
}
