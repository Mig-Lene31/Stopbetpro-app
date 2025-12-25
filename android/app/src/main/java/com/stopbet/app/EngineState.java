package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineState {

    private static final String PREFS = "engine_state";
    private static final String KEY_BLOCKED = "blocked";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void setPendingBlock(Context ctx, boolean value) {
        sp(ctx).edit().putBoolean(KEY_BLOCKED, value).apply();
    }

    public static boolean isPendingBlock(Context ctx) {
        return sp(ctx).getBoolean(KEY_BLOCKED, false);
    }
}
