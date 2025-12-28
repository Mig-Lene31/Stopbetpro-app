package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class DailyTimeEngine {

    private static final String PREFS = "daily_time_engine";
    private static final String KEY_TIME_USED = "time_used_ms";
    private static final long MAX_TIME_MS = 6L * 60 * 60 * 1000; // 6 horas

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void addUsage(Context ctx, long millis) {
        long used = sp(ctx).getLong(KEY_TIME_USED, 0);
        sp(ctx).edit().putLong(KEY_TIME_USED, used + millis).apply();
    }

    public static boolean exceeded(Context ctx) {
        long used = sp(ctx).getLong(KEY_TIME_USED, 0);
        return used >= MAX_TIME_MS;
    }

    public static void reset(Context ctx) {
        sp(ctx).edit().remove(KEY_TIME_USED).apply();
    }
}
