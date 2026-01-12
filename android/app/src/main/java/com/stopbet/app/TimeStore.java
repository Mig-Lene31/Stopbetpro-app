package com.stopbet.app;

import android.content.Context;

public class TimeStore {

    private static final String PREF = "time_store";
    private static final String KEY_START = "start_ts";
    private static final String KEY_LIMIT = "limit_ts";
    private static final String KEY_MINUTES = "minutes";

    public static void setMinutes(Context ctx, int minutes) {
        long now = System.currentTimeMillis();
        long limit = now + (minutes * 60L * 1000L);

        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putInt(KEY_MINUTES, minutes)
                .putLong(KEY_START, now)
                .putLong(KEY_LIMIT, limit)
                .apply();
    }

    public static boolean isExpired(Context ctx) {
        long limit = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_LIMIT, 0);
        return limit > 0 && System.currentTimeMillis() >= limit;
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
