package com.stopbet.app;

import android.content.Context;

public class StopLimitChecker {

    public static void check(Context ctx, double currentBalance) {

        double deposit = DepositStore.get(ctx);
        double stopWin = LimitsStore.getWin(ctx);
        double stopLoss = LimitsStore.getLoss(ctx);

        if (deposit <= 0) return;

        // STOP LOSS
        if (stopLoss > 0) {
            double minAllowed = deposit - stopLoss;
            if (currentBalance <= minAllowed) {
                BlockController.triggerBlock(ctx);
                return;
            }
        }

        // STOP WIN
        if (stopWin > 0) {
            double maxAllowed = deposit + stopWin;
            if (currentBalance >= maxAllowed) {
                BlockController.triggerBlock(ctx);
            }
        }
    }
}
