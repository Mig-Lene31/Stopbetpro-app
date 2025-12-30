package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

public class UserIdentity {

    private static final String PREF = "stopbet_identity";
    private static final String KEY_ID = "user_id";

    public static String getId(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String id = sp.getString(KEY_ID, null);
        if (id == null) {
            id = UUID.randomUUID().toString();
            sp.edit().putString(KEY_ID, id).apply();
        }
        return id;
    }
}
