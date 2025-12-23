package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static void process(Context ctx, float saldoAtual) {

        if (!MotorState.isEnabled(ctx)) return;

        float deposito = AppPrefs.getDepositValue(ctx);
        float stopWin  = AppPrefs.getWinValue(ctx);
        float stopLoss = AppPrefs.getLossValue(ctx);

        boolean bloquear = EngineCore.shouldBlock(
                deposito,
                saldoAtual,
                stopWin,
                stopLoss
        );

        if (bloquear) {
            EngineState.setPendingBlock(ctx, true);
        }
    }
}
