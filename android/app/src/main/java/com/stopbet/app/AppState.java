package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppState {

    private static final String PREFS = "stopbet_state";

    private static final String KEY_DEPOSIT = "deposit";
    private static final String KEY_STOP_WIN = "stop_win";
    private static final String KEY_STOP_LOSS = "stop_loss";
    private static final String KEY_TIME_LIMIT = "time_limit";
    private static final String KEY_BLOCKED = "blocked";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void setDeposit(Context ctx, float value) {
        sp(ctx).edit().putFloat(KEY_DEPOSIT, value).apply();
    }

    public static float getDeposit(Context ctx) {
        return sp(ctx).getFloat(KEY_DEPOSIT, 0f);
    }

    public static void setStopWin(Context ctx, float value) {
        sp(ctx).edit().putFloat(KEY_STOP_WIN, value).apply();
    }

    public static float getStopWin(Context ctx) {
        return sp(ctx).getFloat(KEY_STOP_WIN, 0f);
    }

    public static void setStopLoss(Context ctx, float value) {
        sp(ctx).edit().putFloat(KEY_STOP_LOSS, value).apply();
    }

    public static float getStopLoss(Context ctx) {
        return sp(ctx).getFloat(KEY_STOP_LOSS, 0f);
    }

    public static void setTimeLimit(Context ctx, int minutes) {
        sp(ctx).edit().putInt(KEY_TIME_LIMIT, minutes).apply();
    }

    public static int getTimeLimit(Context ctx) {
        return sp(ctx).getInt(KEY_TIME_LIMIT, 0);
    }

    public static void setBlocked(Context ctx, boolean blocked) {
        sp(ctx).edit().putBoolean(KEY_BLOCKED, blocked).apply();
    }

    public static boolean isBlocked(Context ctx) {
        return sp(ctx).getBoolean(KEY_BLOCKED, false);
    }
}
