package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class RealTimeEngine {

    private static final String PREF = "real_time_engine";
    private static final String KEY_START = "start_time";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static void start(Context ctx) {
        sp(ctx).edit()
                .putLong(KEY_START, System.currentTimeMillis())
                .apply();
    }

    public static void stop(Context ctx) {
        sp(ctx).edit().remove(KEY_START).apply();
    }

    public static long getElapsedMinutes(Context ctx) {
        long start = sp(ctx).getLong(KEY_START, -1);
        if (start <= 0) return 0;
        long diff = System.currentTimeMillis() - start;
        return diff / (60 * 1000);
    }

    public static boolean exceeded(Context ctx) {
        int limit = TimeStore.getMinutes(ctx);
        if (limit <= 0) return false;
        return getElapsedMinutes(ctx) >= limit;
    }
}
