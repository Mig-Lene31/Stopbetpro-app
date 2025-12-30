package com.stopbet.app;

import android.content.Context;
import android.util.Log;

public class EngineRunner {

    public static void run(Context ctx, float saldoAtual) {

        if (!MotorState.isEnabled(ctx)) return;
        if (EngineState.isBlocked(ctx)) return;
        if (!EngineGuard.canUseMotor(ctx)) return;

        float stopWin = AppPrefs.getWinValue(ctx);
        float stopLoss = AppPrefs.getLossValue(ctx);
        float margem = EngineConfig.VALUE_MARGIN;

        boolean atingiuWin = saldoAtual >= (stopWin - margem);
        boolean atingiuLoss = saldoAtual <= (stopLoss + margem);

        if (atingiuWin || atingiuLoss) {
            Log.i("STOPBET_ENGINE", "GATILHO DETECTADO | saldo=" + saldoAtual);
            // BLOQUEIO DESLIGADO POR ENQUANTO
        }
    }
}
