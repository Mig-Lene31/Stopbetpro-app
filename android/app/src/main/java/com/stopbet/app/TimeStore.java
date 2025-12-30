package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeStore {

    private static final String PREF = "stopbet_time";
    private static final String KEY_ACTIVE = "time_active";

    public static void setActive(Context ctx, boolean active) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_ACTIVE, active)
                .apply();
    }

    public static boolean isActive(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_ACTIVE, false);
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
