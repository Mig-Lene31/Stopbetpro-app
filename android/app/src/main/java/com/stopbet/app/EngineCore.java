package com.stopbet.app;

public class EngineCore {

    public static boolean shouldBlock(
            float deposit,
            float currentBalance,
            float stopWin,
            float stopLoss
    ) {

        float winTarget  = deposit + stopWin;
        float lossTarget = deposit - stopLoss;

        float minWin  = winTarget  - EngineConfig.VALUE_MARGIN;
        float maxWin  = winTarget  + EngineConfig.VALUE_MARGIN;

        float minLoss = lossTarget - EngineConfig.VALUE_MARGIN;
        float maxLoss = lossTarget + EngineConfig.VALUE_MARGIN;

        // STOP WIN
        if (currentBalance >= minWin && currentBalance <= maxWin) {
            return true;
        }

        // STOP LOSS
        if (currentBalance >= minLoss && currentBalance <= maxLoss) {
            return true;
        }

        return false;
    }
}
