package com.stopbet.app;

import android.content.Context;

public class DepositStore {

    private static final String PREF = "deposit_store";
    private static final String KEY_DEPOSIT = "deposit";

    public static void set(Context ctx, double value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putFloat(KEY_DEPOSIT, (float) value)
                .apply();
    }

    public static double get(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getFloat(KEY_DEPOSIT, 0f);
    }

    public static boolean hasValue(Context ctx) {
        return get(ctx) > 0;
    }
}
