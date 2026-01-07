package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class MotorState {

    private static final String PREF = "motor_state";
    private static final String ENABLED = "enabled";

    public static void enable(Context ctx) {
        sp(ctx).edit().putBoolean(ENABLED, true).apply();
    }

    public static void forceDisable(Context ctx) {
        sp(ctx).edit().putBoolean(ENABLED, false).apply();
    }

    public static boolean isEnabled(Context ctx) {
        return sp(ctx).getBoolean(ENABLED, false);
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
