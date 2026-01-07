package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineRecoveryStore {

    private static final String PREF = "engine_recovery";
    private static final String LAST_BLOCK = "last_block";

    private static final long BLOCK_TIME_MS = 12 * 60 * 60 * 1000;

    public static void markBlocked(Context ctx) {
        sp(ctx).edit()
            .putLong(LAST_BLOCK, System.currentTimeMillis())
            .apply();
    }

    public static boolean canRecover(Context ctx) {
        long last = sp(ctx).getLong(LAST_BLOCK, 0);
        if (last == 0) return false;
        return (System.currentTimeMillis() - last) >= BLOCK_TIME_MS;
    }

    public static void clear(Context ctx) {
        sp(ctx).edit().clear().apply();
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
