package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppState {

    private static final String PREF = "app_state";
    private static final String KEY_BALANCE = "balance";

    public static float getCurrentBalance(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getFloat(KEY_BALANCE, 0f);
    }

    public static void addBalance(Context ctx, float value) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        float current = prefs.getFloat(KEY_BALANCE, 0f);
        prefs.edit().putFloat(KEY_BALANCE, current + value).apply();
    }

    public static void resetBalance(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putFloat(KEY_BALANCE, 0f)
                .apply();
    }
}
