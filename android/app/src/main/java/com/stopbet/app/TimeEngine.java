package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeEngine {

    private static final String PREFS = "stopbet_time_engine";
    private static final String KEY_START = "start_time";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void startSession(Context ctx) {
        if (!sp(ctx).contains(KEY_START)) {
            sp(ctx).edit()
                .putLong(KEY_START, System.currentTimeMillis())
                .apply();
        }
    }

    public static int getMinutesUsed(Context ctx) {
        long start = sp(ctx).getLong(KEY_START, 0);
        if (start == 0) return 0;

        long diff = System.currentTimeMillis() - start;
        return (int) (diff / 60000);
    }

    public static boolean isTimeExceeded(Context ctx) {
        int limit = AppState.getTimeLimit(ctx);
        if (limit <= 0) return false;

        return getMinutesUsed(ctx) >= limit;
    }

    public static void reset(Context ctx) {
        sp(ctx).edit().remove(KEY_START).apply();
    }
}
