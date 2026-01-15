package com.stopbet.app;

import android.content.Context;
import android.content.Intent;

public class EngineRunner {

    public static void run(Context ctx, double balance) {

        if (EngineState.isBlocked(ctx)) {
            ctx.startService(new Intent(ctx, BetBlockVpnService.class));
            return;
        }

        StopLimitChecker.check(ctx, balance);
    }
}
