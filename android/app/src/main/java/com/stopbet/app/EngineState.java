package com.stopbet.app;

import android.content.Context;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_BLOCK_UNTIL = "block_until";

    public static void activate(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_ACTIVE, true)
                .apply();
    }

    public static boolean isActive(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_ACTIVE, false);
    }

    public static void blockFor12Hours(Context ctx) {
        long until = System.currentTimeMillis() + (12L * 60 * 60 * 1000);
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_BLOCK_UNTIL, until)
                .apply();
    }

    public static long getBlockUntil(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_BLOCK_UNTIL, 0L);
    }

    public static void clearBlock(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .remove(KEY_BLOCK_UNTIL)
                .apply();
    }
}
