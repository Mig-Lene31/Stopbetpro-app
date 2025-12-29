package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineState {

    private static final String PREFS = "engine_state";
    private static final String KEY_BLOCK_UNTIL = "blocked_until";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void blockFor12Hours(Context ctx) {
        long until = System.currentTimeMillis() + (12L * 60 * 60 * 1000);
        sp(ctx).edit().putLong(KEY_BLOCK_UNTIL, until).apply();
    }

    public static boolean isBlocked(Context ctx) {
        long until = sp(ctx).getLong(KEY_BLOCK_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static long getRemainingTime(Context ctx) {
        long until = sp(ctx).getLong(KEY_BLOCK_UNTIL, 0);
        long now = System.currentTimeMillis();
        return Math.max(0, until - now);
    }

    public static void unlock(Context ctx) {
        sp(ctx).edit().remove(KEY_BLOCK_UNTIL).apply();
    }
}
