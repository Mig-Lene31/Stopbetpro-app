package com.stopbet.app;

import android.content.Context;

public class TimeStore {

    private static final String PREF = "time_store";
    private static final String KEY_MINUTES = "minutes";

    public static void setMinutes(Context ctx, int minutes) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putInt(KEY_MINUTES, minutes)
                .apply();
    }

    public static int getMinutes(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getInt(KEY_MINUTES, 0);
    }

    public static boolean hasTimeLimit(Context ctx) {
        return getMinutes(ctx) > 0;
    }

    public static boolean isExpired(Context ctx) {
        if (!MotorStateStore.isActive(ctx)) return false;

        long start = MotorStateStore.getStartedAt(ctx);
        if (start == 0) return false;

        long limitMs = getMinutes(ctx) * 60000L;
        return System.currentTimeMillis() - start >= limitMs;
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
