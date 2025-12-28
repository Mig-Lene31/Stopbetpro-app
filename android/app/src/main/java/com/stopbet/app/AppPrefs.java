package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {

    private static final String PREFS = "stopbet_prefs";

    private static final String KEY_WIN  = "stop_win";
    private static final String KEY_LOSS = "stop_loss";
    private static final String KEY_TIME = "limit_time";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    // ===== WIN / LOSS (STRING) =====
    public static void setWin(Context ctx, String value) {
        sp(ctx).edit().putString(KEY_WIN, value).apply();
    }

    public static void setLoss(Context ctx, String value) {
        sp(ctx).edit().putString(KEY_LOSS, value).apply();
    }

    public static String getWin(Context ctx) {
        return sp(ctx).getString(KEY_WIN, "0");
    }

    public static String getLoss(Context ctx) {
        return sp(ctx).getString(KEY_LOSS, "0");
    }

    // ===== WIN / LOSS (FLOAT) =====
    public static float getWinValue(Context ctx) {
        try {
            return Float.parseFloat(getWin(ctx));
        } catch (Exception e) {
            return 0f;
        }
    }

    public static float getLossValue(Context ctx) {
        try {
            return Float.parseFloat(getLoss(ctx));
        } catch (Exception e) {
            return 0f;
        }
    }

    // ===== TEMPO =====
    public static void setTime(Context ctx, String value) {
        sp(ctx).edit().putString(KEY_TIME, value).apply();
    }

    public static String getTime(Context ctx) {
        return sp(ctx).getString(KEY_TIME, "0");
    }
}
