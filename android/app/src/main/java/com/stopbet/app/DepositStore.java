package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class DepositStore {

    private static final String PREF = "stopbet_deposit";
    private static final String KEY = "value";

    public static void setValue(Context ctx, String value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY, value)
            .apply();
    }

    public static String getValue(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .getString(KEY, "0");
    }
}

    public static boolean hasValue(Context ctx) {
        String v = getValue(ctx);
        return v != null && !v.trim().isEmpty();
    }

