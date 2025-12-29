package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class LicenseState {

    private static final String PREFS = "license_state";
    private static final String KEY_UNTIL = "license_until";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void grant30Days(Context ctx) {
        long until = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000);
        sp(ctx).edit().putLong(KEY_UNTIL, until).apply();
    }

    public static boolean isValid(Context ctx) {
        long until = sp(ctx).getLong(KEY_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static long remaining(Context ctx) {
        long until = sp(ctx).getLong(KEY_UNTIL, 0);
        return Math.max(0, until - System.currentTimeMillis());
    }

    public static void clear(Context ctx) {
        sp(ctx).edit().remove(KEY_UNTIL).apply();
    }
}
