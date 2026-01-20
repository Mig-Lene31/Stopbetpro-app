package com.stopbet.app;

import android.content.Context;

public class MotorStateStore {

    private static final String PREF = "motor_state";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_STARTED_AT = "started_at";

    public static void setRunning(Context ctx, boolean running) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_ACTIVE, running)
                .putLong(KEY_STARTED_AT, running ? System.currentTimeMillis() : 0)
                .apply();
    }

    public static boolean isRunning(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_ACTIVE, false);
    }

    // ===== COMPATIBILIDADE LEGADA =====

    public static boolean isActive(Context ctx) {
        return isRunning(ctx);
    }

    public static long getStartedAt(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_STARTED_AT, 0);
    }

    public static boolean isEnabled(Context ctx) {
        return isRunning(ctx);
    }

    public static void setEnabled(Context ctx, boolean enabled) {
        setRunning(ctx, enabled);
    }
}
