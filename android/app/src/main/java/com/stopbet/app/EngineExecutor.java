package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static void process(Context ctx, float saldo) {

        // Motor desligado = não executa nada
        if (!MotorState.isEnabled(ctx)) {
            return;
        }

        // Limite diário
        if (DailyTimeEngine.exceeded(ctx)) {
            EngineState.blockFor12Hours(ctx);
            return;
        }

        // Stop Win / Stop Loss
        float win = LimitsState.getWin(ctx);
        float loss = LimitsState.getLoss(ctx);

        if (saldo >= win || saldo <= loss) {
            EngineState.blockFor12Hours(ctx);
        }
    }
}
