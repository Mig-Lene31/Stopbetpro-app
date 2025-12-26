package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineState {

    private static final String PREFS = "engine_state";
    private static final String KEY_BLOCKED_UNTIL = "blocked_until";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void blockFor12Hours(Context ctx) {
        long now = System.currentTimeMillis();
        long blockedUntil = now + (12L * 60 * 60 * 1000);
        sp(ctx).edit().putLong(KEY_BLOCKED_UNTIL, blockedUntil).apply();
    }

    public static boolean isBlocked(Context ctx) {
        long until = sp(ctx).getLong(KEY_BLOCKED_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static long getRemainingMillis(Context ctx) {
        long until = sp(ctx).getLong(KEY_BLOCKED_UNTIL, 0);
        long now = System.currentTimeMillis();
        return Math.max(0, until - now);
    }

    public static void clearBlock(Context ctx) {
        sp(ctx).edit().remove(KEY_BLOCKED_UNTIL).apply();
    }
}
