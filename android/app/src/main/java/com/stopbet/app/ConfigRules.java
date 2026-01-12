package com.stopbet.app;

import android.content.Context;

public class ConfigRules {

    public static void onSaveLimits(Context ctx) {
        TimeStore.clear(ctx);
    }

    public static void onSaveTime(Context ctx) {
        LimitsStore.clear(ctx);
    }

    public static boolean isReadyToActivate(Context ctx) {
        if (!DepositStore.hasValue(ctx)) return false;

        boolean hasLimits = LimitsStore.hasLimits(ctx);
        boolean hasTime = TimeStore.hasTimeLimit(ctx);

        return hasLimits ^ hasTime;
    }
}
