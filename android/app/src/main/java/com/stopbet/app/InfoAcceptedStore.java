package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class InfoAcceptedStore {

    private static final String PREF = "kairos_prefs";
    private static final String KEY_ACCEPTED = "info_accepted";

    public static boolean hasAccepted(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_ACCEPTED, false);
    }

    public static void markAccepted(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_ACCEPTED, true).apply();
    }
}
