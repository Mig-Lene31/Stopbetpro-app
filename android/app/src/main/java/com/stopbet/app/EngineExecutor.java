package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static void process(Context c, float saldo) {

        if (!MotorState.isEnabled(c)) return;

        float win = LimitsState.getWin(c);
        float loss = LimitsState.getLoss(c);

        if (saldo >= win || saldo <= loss) {
            MotorState.forceDisable(c);
            EngineState.blockFor12Hours(c);
        }
    }
}
