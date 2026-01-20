package com.stopbet.app;

import android.content.Context;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String KEY_BLOCK_UNTIL = "block_until";

    public static void blockFor12Hours(Context ctx) {
        long until = System.currentTimeMillis() + (12L * 60L * 60L * 1000L);
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_BLOCK_UNTIL, until)
                .apply();
    }

    public static boolean isBlocked(Context ctx) {
        return getBlockUntil(ctx) > System.currentTimeMillis();
    }

    public static long getBlockUntil(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_BLOCK_UNTIL, 0);
    }

    public static void clearBlock(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
