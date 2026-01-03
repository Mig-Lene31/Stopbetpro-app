package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String KEY_BLOCKED_UNTIL = "blocked_until";
    private static final String KEY_BLOCK_REASON = "block_reason";

    public static final String REASON_NONE = "NONE";
    public static final String REASON_STOP_WIN = "STOP_WIN";
    public static final String REASON_STOP_LOSS = "STOP_LOSS";
    public static final String REASON_STOP_TIME = "STOP_TIME";

    public static boolean isBlocked(Context ctx) {
        return System.currentTimeMillis() < getBlockedUntil(ctx);
    }

    public static void blockFor12Hours(Context ctx, String reason) {
        long until = System.currentTimeMillis() + (12L * 60 * 60 * 1000);
        SharedPreferences.Editor ed =
                ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit();

        ed.putLong(KEY_BLOCKED_UNTIL, until);
        ed.putString(KEY_BLOCK_REASON, reason);
        ed.apply();
    }

    public static void unlockFor30Days(Context ctx) {
        long until = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000);
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_BLOCKED_UNTIL, until)
                .putString(KEY_BLOCK_REASON, REASON_NONE)
                .apply();
    }

    public static void clearBlock(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_BLOCKED_UNTIL, 0)
                .putString(KEY_BLOCK_REASON, REASON_NONE)
                .apply();
    }

    public static String getBlockReason(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getString(KEY_BLOCK_REASON, REASON_NONE);
    }

    private static long getBlockedUntil(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_BLOCKED_UNTIL, 0);
    }
}
