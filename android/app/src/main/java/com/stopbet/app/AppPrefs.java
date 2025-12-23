package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

public class AppPrefs {

    private static final String PREFS = "stopbet_prefs";
    private static final String KEY_ID = "user_id";

    public static String getUserId(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String id = sp.getString(KEY_ID, null);

        if (id == null) {
            id = "SBP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            sp.edit().putString(KEY_ID, id).apply();
        }

        return id;
    }
}
