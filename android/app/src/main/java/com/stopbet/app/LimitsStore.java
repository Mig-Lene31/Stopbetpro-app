package com.stopbet.app;

import android.content.Context;

public class LimitsStore {

    private static final String PREF = "limits";
    private static final String KEY_WIN = "win";
    private static final String KEY_LOSS = "loss";

    public static void setWin(Context ctx, float value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putFloat(KEY_WIN, value)
                .apply();
    }

    public static void setLoss(Context ctx, float value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putFloat(KEY_LOSS, value)
                .apply();
    }

    public static float getWin(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getFloat(KEY_WIN, 0f);
    }

    public static float getLoss(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getFloat(KEY_LOSS, 0f);
    }

    public static boolean hasLimits(Context ctx) {
        return getWin(ctx) > 0 && getLoss(ctx) > 0;
    }

    public static boolean isConfigured(Context ctx) {
        return hasLimits(ctx);
    }
}
