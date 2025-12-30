package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AdminSession {

    private static final String PREF = "admin_session";
    private static final String KEY = "unlocked";

    public static void setUnlocked(Context ctx, boolean value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY, value)
                .apply();
    }

    public static boolean isUnlocked(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY, false);
    }

    public static void lock(Context ctx) {
        setUnlocked(ctx, false);
    }
}
