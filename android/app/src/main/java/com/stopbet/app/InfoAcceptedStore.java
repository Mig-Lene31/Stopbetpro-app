package com.stopbet.app;

import android.content.Context;

public class InfoAcceptedStore {

    private static final String PREF = "info_accept";
    private static final String KEY = "accepted";

    public static void setAccepted(Context ctx, boolean value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY, value)
                .apply();
    }

    public static boolean isAccepted(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY, false);
    }
}
