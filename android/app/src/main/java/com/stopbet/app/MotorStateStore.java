package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class MotorStateStore {

    private static final String PREF = "motor_state";
    private static final String KEY = "enabled";

    public static boolean isEnabled(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY, false);
    }

    public static void setEnabled(Context ctx, boolean enabled) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY, enabled)
                .apply();
    }
}
