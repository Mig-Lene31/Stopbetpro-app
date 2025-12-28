package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static void process(Context ctx, float saldoAtual) {

        if (!EngineGuard.canUseMotor(ctx)) return;

        // conta 1 minuto por uso (tempo diário)
        DailyTimeEngine.addUsage(ctx, 60_000);

        if (DailyTimeEngine.exceeded(ctx)) {
            EngineState.blockFor12Hours(ctx);
            MotorState.setEnabled(ctx, false);
            return;
        }

        float stopWin  = AppPrefs.getWinValue(ctx);
        float stopLoss = AppPrefs.getLossValue(ctx);

        if (stopWin > 0 && saldoAtual >= stopWin) {
            EngineState.blockFor12Hours(ctx);
            MotorState.setEnabled(ctx, false);
        }

        if (stopLoss > 0 && saldoAtual <= stopLoss) {
            EngineState.blockFor12Hours(ctx);
            MotorState.setEnabled(ctx, false);
        }
    }
}
