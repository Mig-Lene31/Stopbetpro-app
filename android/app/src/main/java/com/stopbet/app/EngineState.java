package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String KEY_BLOCKED = "blocked";
    private static final String KEY_BLOCK_START = "block_start";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static boolean isBlocked(Context ctx) {
        return sp(ctx).getBoolean(KEY_BLOCKED, false);
    }

    public static void block(Context ctx) {
        sp(ctx).edit()
                .putBoolean(KEY_BLOCKED, true)
                .putLong(KEY_BLOCK_START, System.currentTimeMillis())
                .apply();
        MotorState.setEnabled(ctx, false);
    }

    public static void adminUnlock(Context ctx) {
        sp(ctx).edit()
                .putBoolean(KEY_BLOCKED, false)
                .remove(KEY_BLOCK_START)
                .apply();
        MotorState.setEnabled(ctx, false);
    }

    public static long getBlockedAt(Context ctx) {
        return sp(ctx).getLong(KEY_BLOCK_START, 0);
    }
}
