package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static boolean process(Context ctx, float saldo) {

        if (!MotorGuard.canRun(ctx)) return false;
        if (!LimitsStore.hasLimits(ctx)) return false;

        float win = LimitsStore.getWin(ctx);
        float loss = LimitsStore.getLoss(ctx);
        float margem = 5f;

        boolean bateuWin  = win >= 0 && saldo >= (win - margem);
        boolean bateuLoss = loss >= 0 && saldo <= (loss + margem);

        if (bateuWin) {
            MotorState.forceDisable(ctx);
            BlockController.block(ctx, EngineState.REASON_STOP_WIN);
            return true;
        }

        if (bateuLoss) {
            MotorState.forceDisable(ctx);
            BlockController.block(ctx, EngineState.REASON_STOP_LOSS);
            return true;
        }

        return false;
    }
}
