package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class BalanceStabilityEngine {

    private static final String PREF = "balance_stability";
    private static final String LAST_VALUE = "last_value";
    private static final String COUNT = "count";
    private static final String FIRST_TIME = "first_time";

    private static final int REQUIRED_HITS = 3;
    private static final long WINDOW_MS = 9000;
    private static final float MARGIN = 5f;

    public static boolean push(Context ctx, float value) {

        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        long now = System.currentTimeMillis();
        long first = sp.getLong(FIRST_TIME, 0);
        float last = sp.getFloat(LAST_VALUE, -1);
        int count = sp.getInt(COUNT, 0);

        if (first == 0 || (now - first) > WINDOW_MS) {
            reset(sp, value, now);
            return false;
        }

        if (Math.abs(last - value) <= MARGIN) {
            count++;
            sp.edit().putInt(COUNT, count).apply();
            return count >= REQUIRED_HITS;
        }

        reset(sp, value, now);
        return false;
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply();
    }

    private static void reset(SharedPreferences sp, float value, long now) {
        sp.edit()
            .putFloat(LAST_VALUE, value)
            .putInt(COUNT, 1)
            .putLong(FIRST_TIME, now)
            .apply();
    }
}
