package com.stopbet.app;

import android.content.Context;

public class MotorStateStore {

    private static final String PREF = "motor_state";
    private static final String KEY_ACTIVE = "active";

    public static void setRunning(Context ctx, boolean running) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_ACTIVE, running)
                .apply();
    }

    public static boolean isRunning(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_ACTIVE, false);
    }
}
