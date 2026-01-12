package com.stopbet.app;

import android.content.Context;

public class AppProtectionState {

    private static final String PREF = "app_protection_state";

    private static final String KEY_INFO_ACCEPTED = "info_accepted";
    private static final String KEY_CONFIG_READY = "config_ready";
    private static final String KEY_MOTOR_ENABLED = "motor_enabled";
    private static final String KEY_CRITICAL_ERROR = "critical_error";

    public static void setInfoAccepted(Context ctx, boolean value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_INFO_ACCEPTED, value)
                .apply();
    }

    public static boolean isInfoAccepted(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_INFO_ACCEPTED, false);
    }

    public static void setConfigReady(Context ctx, boolean value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_CONFIG_READY, value)
                .apply();
    }

    public static boolean isConfigReady(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_CONFIG_READY, false);
    }

    public static void setMotorEnabled(Context ctx, boolean value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_MOTOR_ENABLED, value)
                .apply();
    }

    public static boolean isMotorEnabled(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_MOTOR_ENABLED, false);
    }

    public static void setCriticalError(Context ctx, boolean value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_CRITICAL_ERROR, value)
                .apply();
    }

    public static boolean hasCriticalError(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_CRITICAL_ERROR, false);
    }

    public static boolean canAppRun(Context ctx) {
        return !hasCriticalError(ctx);
    }

    public static boolean canMotorRun(Context ctx) {
        return isInfoAccepted(ctx)
                && isConfigReady(ctx)
                && isMotorEnabled(ctx)
                && !hasCriticalError(ctx);
    }

    public static void resetAll(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
