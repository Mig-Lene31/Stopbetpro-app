package com.stopbet.app;

import android.content.Context;

public class DepositStore {

    private static final String PREF = "deposit_store";
    private static final String KEY_VALUE = "deposit_value";

    public static float get(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getFloat(KEY_VALUE, 0f);
    }

    public static void set(Context ctx, float value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putFloat(KEY_VALUE, value)
                .apply();
    }

    public static boolean hasValue(Context ctx) {
        return get(ctx) > 0;
    }
}
