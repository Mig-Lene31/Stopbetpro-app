package com.stopbet.app;

import android.content.Context;

public class ConfigValidator {

    public static String validate(Context ctx) {

        if (DepositStore.get(ctx) <= 0) {
            return "Informe o valor do depósito";
        }

        boolean hasStopValue =
                LimitsStore.getWin(ctx) > 0 ||
                LimitsStore.getLoss(ctx) > 0;

        boolean hasStopTime =
                TimeStore.hasTimeLimit(ctx);

        if (!hasStopValue && !hasStopTime) {
            return "Defina um Stop de tempo ou de valor";
        }

        return null;
    }
}
