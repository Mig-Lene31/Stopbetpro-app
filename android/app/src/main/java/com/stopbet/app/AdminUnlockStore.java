package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AdminUnlockStore {

    private static final String PREF = "admin_unlock";
    private static final String KEY_ID = "authorized_id";

    public static void saveAuthorizedId(Context ctx, String id) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_ID, id)
                .apply();
    }

    public static boolean isAuthorized(Context ctx) {
        String savedId = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getString(KEY_ID, null);

        if (savedId == null) return false;

        return savedId.equals(UserIdentity.getId(ctx));
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
