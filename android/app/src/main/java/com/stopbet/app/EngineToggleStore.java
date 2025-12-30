package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineToggleStore {

    private static final String PREF = "engine_toggle";
    private static final String KEY = "enabled";

    public static void set(Context ctx, boolean value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY, value)
            .apply();
    }

    public static boolean isEnabled(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .getBoolean(KEY, false);
    }
}
