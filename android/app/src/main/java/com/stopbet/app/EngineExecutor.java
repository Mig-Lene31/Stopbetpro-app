import com.stopbet.app.MotorGuard;
package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static boolean process(Context ctx, float saldo) {
        if (!MotorGuard.canRun(ctx)) return false;

        if (!MotorState.isEnabled(ctx)) return false;
        if (!LimitsStore.hasLimits(ctx)) return false;

        float win = LimitsStore.getWin(ctx);
        float loss = LimitsStore.getLoss(ctx);
        float m = EngineConfig.VALUE_MARGIN;

        boolean bateuWin  = (win >= 0)  && saldo >= (win - m);
        boolean bateuLoss = (loss <= 0) && saldo <= (loss + m);

        if (bateuWin || bateuLoss) {
            MotorState.forceDisable(ctx);
            EngineState.blockFor12Hours(ctx);
            return true;
        }

        return false;
    }
}
