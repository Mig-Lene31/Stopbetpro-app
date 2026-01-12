package com.stopbet.app;

import android.content.Context;

public class TimeStore {

    private static final String PREF = "time_store";
    private static final String KEY_LIMIT = "limit_ts";
    private static final String KEY_MINUTES = "minutes";

    public static void setMinutes(Context ctx, int minutes) {
        long now = System.currentTimeMillis();
        long limit = now + minutes * 60000L;

        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putInt(KEY_MINUTES, minutes)
                .putLong(KEY_LIMIT, limit)
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
        long limit = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_LIMIT, 0);
        return limit > 0 && System.currentTimeMillis() >= limit;
    }

    public static int getUsedMinutesToday(Context ctx) {
        long limit = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_LIMIT, 0);

        if (limit == 0) return 0;

        long now = System.currentTimeMillis();
        long total = limit - now;
        long used = (getMinutes(ctx) * 60000L) - total;

        return used < 0 ? 0 : (int) (used / 60000L);
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
