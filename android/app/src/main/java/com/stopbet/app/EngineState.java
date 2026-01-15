package com.stopbet.app;

import android.content.Context;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String KEY_BLOCK_UNTIL = "block_until";
    private static final long BLOCK_12H = 12L * 60L * 60L * 1000L;

    public static void blockFor12Hours(Context ctx) {
        long until = System.currentTimeMillis() + BLOCK_12H;
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_BLOCK_UNTIL, until)
                .apply();
    }

    public static boolean isBlocked(Context ctx) {
        long until = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_BLOCK_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static long getRemainingMs(Context ctx) {
        long until = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_BLOCK_UNTIL, 0);
        return Math.max(0, until - System.currentTimeMillis());
    }

    // ADM ONLY
    public static void forceUnlock(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .remove(KEY_BLOCK_UNTIL)
                .apply();
    }
}
