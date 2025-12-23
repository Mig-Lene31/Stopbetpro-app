package com.stopbet.app;

import android.content.Context;

public class EngineRunner {

    public static void run(Context ctx, float saldoAtual) {

        if (!MotorState.isEnabled(ctx)) return;

        float win = AppPrefs.getWinValue(ctx);
        float loss = AppPrefs.getLossValue(ctx);

        if (saldoAtual >= win || saldoAtual <= loss) {
            // AQUI entra bloqueio na próxima fase
        }
    }
}
