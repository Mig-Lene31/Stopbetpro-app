package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class DepositStore {

    private static final String PREF = "deposit_store";
    private static final String VALUE = "value";

    public static void setValue(Context ctx, String v) {
        sp(ctx).edit().putString(VALUE, v).apply();
    }

    public static String getValue(Context ctx) {
        return sp(ctx).getString(VALUE, null);
    }

    public static boolean hasValue(Context ctx) {
        String v = getValue(ctx);
        return v != null && !v.trim().isEmpty();
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
