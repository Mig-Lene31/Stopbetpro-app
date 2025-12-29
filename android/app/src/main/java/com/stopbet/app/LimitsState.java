package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class LimitsState {

    private static final String PREFS = "limits_state";
    private static final String KEY_WIN = "limit_win";
    private static final String KEY_LOSS = "limit_loss";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void setWin(Context ctx, float value) {
        sp(ctx).edit().putFloat(KEY_WIN, value).apply();
    }

    public static void setLoss(Context ctx, float value) {
        sp(ctx).edit().putFloat(KEY_LOSS, value).apply();
    }

    public static float getWin(Context ctx) {
        return sp(ctx).getFloat(KEY_WIN, Float.MAX_VALUE);
    }

    public static float getLoss(Context ctx) {
        return sp(ctx).getFloat(KEY_LOSS, -Float.MAX_VALUE);
    }
}
