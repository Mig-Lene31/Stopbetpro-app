package com.stopbet.app;

import android.content.Context;

public class BalanceStabilityEngine {

    private static final String PREF = "balance_stability";
    private static final String KEY_LAST = "last";
    private static final String KEY_COUNT = "count";

    private static final double MARGIN = 0.01;
    private static final int REQUIRED_HITS = 3;

    public static boolean isStable(Context ctx, double value) {

        var prefs = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        double last = prefs.getFloat(KEY_LAST, -1f);
        int count = prefs.getInt(KEY_COUNT, 0);

        if (last < 0 || Math.abs(last - value) > MARGIN) {
            prefs.edit()
                    .putFloat(KEY_LAST, (float) value)
                    .putInt(KEY_COUNT, 1)
                    .apply();
            return false;
        }

        count++;
        prefs.edit().putInt(KEY_COUNT, count).apply();

        return count >= REQUIRED_HITS;
    }
}
