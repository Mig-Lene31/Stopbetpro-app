package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class LimitsStore {

    private static final String PREF = "limits_store";
    private static final String WIN = "win";
    private static final String LOSS = "loss";

    public static void saveWin(Context ctx, float v) {
        sp(ctx).edit().putFloat(WIN, v).apply();
    }

    public static void saveLoss(Context ctx, float v) {
        sp(ctx).edit().putFloat(LOSS, v).apply();
    }

    public static float getWin(Context ctx) {
        return sp(ctx).getFloat(WIN, -1);
    }

    public static float getLoss(Context ctx) {
        return sp(ctx).getFloat(LOSS, -1);
    }

    public static boolean hasLimits(Context ctx) {
        return getWin(ctx) >= 0 || getLoss(ctx) >= 0;
    }

    public static void clear(Context ctx) {
        sp(ctx).edit().clear().apply();
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
