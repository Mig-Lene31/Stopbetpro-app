package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class MotorState {

    private static final String PREFS = "stopbet_state";
    private static final String KEY_MOTOR_ENABLED = "motor_enabled";
    private static final String KEY_MOTOR_TEST = "motor_test_mode";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    // MOTOR ON / OFF
    public static void setEnabled(Context ctx, boolean v) {
        sp(ctx).edit().putBoolean(KEY_MOTOR_ENABLED, v).apply();
    }

    public static boolean isEnabled(Context ctx) {
        return sp(ctx).getBoolean(KEY_MOTOR_ENABLED, false);
    }

    // MODO TESTE / REAL
    public static void setTestMode(Context ctx, boolean v) {
        sp(ctx).edit().putBoolean(KEY_MOTOR_TEST, v).apply();
    }

    public static boolean isTestMode(Context ctx) {
        return sp(ctx).getBoolean(KEY_MOTOR_TEST, true);
    }
}
