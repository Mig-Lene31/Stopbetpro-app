package com.stopbet.app;

import android.content.Context;

public class MotorStateStore {

    private static final String PREF = "motor_state";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_STARTED_AT = "started_at";

    public static void activate(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_ACTIVE, true)
                .putLong(KEY_STARTED_AT, System.currentTimeMillis())
                .apply();
    }

    public static void deactivate(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_ACTIVE, false)
                .apply();
    }

    public static boolean isActive(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_ACTIVE, false);
    }

    public static long getStartedAt(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_STARTED_AT, 0);
    }
}
