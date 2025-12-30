package com.stopbet.app;

import android.content.Context;
import android.content.Intent;

public class EngineRunner {

    public static void run(Context ctx, float saldoAtual) {

        if (!MotorState.isEnabled(ctx)) return;
        if (!EngineGuard.canUseMotor(ctx)) return;

        float win = AppPrefs.getWinValue(ctx);
        float loss = AppPrefs.getLossValue(ctx);

        if (saldoAtual >= win || saldoAtual <= loss) {
            EngineState.blockFor12Hours(ctx);

            Intent i = new Intent(ctx, BlockedActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ctx.startActivity(i);
        }
    }
}
