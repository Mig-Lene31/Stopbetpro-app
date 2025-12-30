package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class InfoAcceptedStore {

    private static final String PREF = "info_accept";
    private static final String KEY = "accepted";

    public static void accept(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY, true)
                .apply();
    }

    public static boolean hasAccepted(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY, false);
    }
}
