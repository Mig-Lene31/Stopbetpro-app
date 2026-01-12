package com.stopbet.app;

import android.content.Context;

public class BalanceConfirmState {

    private static final String PREF = "balance_confirm";
    private static final String KEY = "confirmed";

    public static boolean isConfirmed(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY, false);
    }

    public static void confirm(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit().putBoolean(KEY, true).apply();
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit().remove(KEY).apply();
    }
}
