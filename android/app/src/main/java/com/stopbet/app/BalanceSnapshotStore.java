package com.stopbet.app;

import android.content.Context;

public class BalanceSnapshotStore {

    private static final String PREF = "balance_snapshot";
    private static final String KEY_VALUE = "last_balance";

    public static void save(Context ctx, double value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putFloat(KEY_VALUE, (float) value)
                .apply();
    }

    public static double get(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getFloat(KEY_VALUE, -1f);
    }
}
