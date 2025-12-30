package com.stopbet.app;

import android.content.Context;
import android.content.Intent;

public class EngineRunner {

    public static void run(Context ctx, float saldoAtual) {

        // evita múltiplos disparos
        if (EngineState.isBlocked(ctx)) return;

        // motor desligado = não faz nada
        if (!MotorState.isEnabled(ctx)) return;

        // alguma regra global impede o motor
        if (!EngineGuard.canUseMotor(ctx)) return;

        float stopWin = AppPrefs.getWinValue(ctx);
        float stopLoss = AppPrefs.getLossValue(ctx);

        float margem = EngineConfig.VALUE_MARGIN;

        boolean atingiuWin = saldoAtual >= (stopWin - margem);
        boolean atingiuLoss = saldoAtual <= (stopLoss + margem);

        if (atingiuWin || atingiuLoss) {
            EngineState.blockFor12Hours(ctx);

            Intent i = new Intent(ctx, BlockedActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ctx.startActivity(i);
        }
    }
}
