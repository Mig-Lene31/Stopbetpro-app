package com.stopbet.app;

import android.content.Context;

public class EngineRunner {

    public static void run(Context ctx, float currentBalance) {

        if (!AppState.isEngineEnabled(ctx)) return;
        if (AppState.isBlocked(ctx)) return;

        float deposit  = AppState.getDeposit(ctx);
        float stopWin  = AppState.getStopWin(ctx);
        float stopLoss = AppState.getStopLoss(ctx);

        if (deposit <= 0 || stopWin <= 0 || stopLoss <= 0) return;

        boolean shouldBlock = EngineCore.shouldBlock(
                deposit,
                currentBalance,
                stopWin,
                stopLoss
        );

        EngineExecutor.handleDecision(ctx, shouldBlock);
    }
}
