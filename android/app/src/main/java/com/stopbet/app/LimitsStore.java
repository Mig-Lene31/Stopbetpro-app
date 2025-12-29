package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class LimitsStore {

    private static final String PREF = "limits_store";
    private static final String KEY_WIN = "stop_win";
    private static final String KEY_LOSS = "stop_loss";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static void saveWin(Context ctx, float value) {
        sp(ctx).edit().putFloat(KEY_WIN, value).apply();
    }

    public static void saveLoss(Context ctx, float value) {
        sp(ctx).edit().putFloat(KEY_LOSS, value).apply();
    }

    public static float getWin(Context ctx) {
        return sp(ctx).getFloat(KEY_WIN, 0f);
    }

    public static float getLoss(Context ctx) {
        return sp(ctx).getFloat(KEY_LOSS, 0f);
    }
}
