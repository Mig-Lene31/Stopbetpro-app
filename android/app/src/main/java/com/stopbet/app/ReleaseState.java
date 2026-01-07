package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class ReleaseState {

    private static final String PREF = "release_state";
    private static final String KEY = "released";

    public static void markReleased(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY, true)
            .apply();
    }

    public static boolean isReleased(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .getBoolean(KEY, false);
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply();
    }
}
