package com.stopbet.app;

import android.content.Context;
import android.content.Intent;

public class EngineRunner {

    public static void run(Context ctx, float saldo) {

        if (!EngineGuard.canUseMotor(ctx)) return;

        Intent i = new Intent(ctx, EngineService.class);
        i.putExtra("saldo", saldo);
        ctx.startService(i);
    }
}
