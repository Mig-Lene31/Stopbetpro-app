package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static void process(Context ctx, float saldoAtual) {

        if (!MotorState.isEnabled(ctx)) return;

        float stopWin  = AppPrefs.getWinValue(ctx);
        float stopLoss = AppPrefs.getLossValue(ctx);

        if (stopWin > 0 && saldoAtual >= stopWin) {
            EngineState.setPendingBlock(ctx, true);
            MotorState.setEnabled(ctx, false);
        }

        if (stopLoss > 0 && saldoAtual <= stopLoss) {
            EngineState.setPendingBlock(ctx, true);
            MotorState.setEnabled(ctx, false);
        }
    }
}
