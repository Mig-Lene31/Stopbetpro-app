package com.stopbet.app;

import android.content.Context;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String KEY_BLOCKED_UNTIL = "blocked_until";

    public static void blockFor12Hours(Context ctx) {
        long until = System.currentTimeMillis() + (12 * 60 * 60 * 1000);
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_BLOCKED_UNTIL, until)
                .apply();
    }

    public static boolean isBlocked(Context ctx) {
        long until = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_BLOCKED_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static void clearBlock(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .remove(KEY_BLOCKED_UNTIL)
                .apply();
    }
}
