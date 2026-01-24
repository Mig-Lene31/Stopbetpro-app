package com.stopbet.app;

import android.content.Context;

public class InterventionCounterStore {

    private static final String PREF = "intervention_counter";
    private static final String KEY_COUNT = "count";

    public static int increment(Context ctx) {
        int current = get(ctx) + 1;
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putInt(KEY_COUNT, current)
                .apply();
        return current;
    }

    public static int get(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getInt(KEY_COUNT, 0);
    }

    public static void reset(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putInt(KEY_COUNT, 0)
                .apply();
    }
}
