package com.stopbet.app;

import android.content.Context;

public class BalanceEngineBridge {

    public static void onStableBalance(Context ctx, double balance) {

        if (!MotorStateStore.isRunning(ctx)) return;

        if (EngineState.isBlocked(ctx)) return;

        EngineRunner.run(ctx, balance);
    }
}
