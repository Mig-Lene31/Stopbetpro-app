package com.stopbet.app;

import android.content.Context;

public class UserAccessStore {

    private static final String PREF = "user_access";
    private static final String KEY_AUTH = "authorized";

    public static void authorize(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_AUTH, true)
                .apply();
    }

    public static boolean isAuthorized(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_AUTH, false);
    }
}
