package com.stopbet.app;

import android.content.Context;

public class AppState {

    private static final String PREF = "app_state";
    private static final String KEY_BALANCE = "balance";

    public static void setBalance(Context ctx, double value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putFloat(KEY_BALANCE, (float) value)
                .apply();
    }

    public static double getBalance(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getFloat(KEY_BALANCE, 0f);
    }
}
