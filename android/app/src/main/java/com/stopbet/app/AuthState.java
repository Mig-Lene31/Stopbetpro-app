package com.stopbet.app;

import android.content.Context;

public class AuthState {

    private static final String PREF = "auth_state";
    private static final String KEY_AUTHORIZED_UNTIL = "authorized_until";

    public static void authorizeFor30Days(Context ctx) {
        long until = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000);
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_AUTHORIZED_UNTIL, until)
                .apply();
    }

    public static boolean isAuthorized(Context ctx) {
        long until = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_AUTHORIZED_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .remove(KEY_AUTHORIZED_UNTIL)
                .apply();
    }
}
