package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public final class EngineState {

    public static final int REASON_NONE = 0;
    public static final int REASON_STOP_TIME = 1;
    public static final int REASON_STOP_WIN = 2;
    public static final int REASON_STOP_LOSS = 3;

    private static final String PREFS = "engine_state";
    private static final String KEY_BLOCKED = "blocked";
    private static final String KEY_REASON = "reason";

    public static void block(Context ctx, int reason) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        sp.edit()
                .putBoolean(KEY_BLOCKED, true)
                .putInt(KEY_REASON, reason)
                .apply();
    }

    public static void clearBlock(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        sp.edit()
                .putBoolean(KEY_BLOCKED, false)
                .putInt(KEY_REASON, REASON_NONE)
                .apply();
    }

    public static boolean isBlocked(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_BLOCKED, false);
    }

    public static int getReason(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return sp.getInt(KEY_REASON, REASON_NONE);
    }
}
