package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class UserIdentity {

    private static final String PREFS = "user_identity";
    private static final String KEY_ID = "user_id";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static String getId(Context ctx) {
        String id = sp(ctx).getString(KEY_ID, null);

        if (id == null) {
            id = UUID.randomUUID().toString();
            sp(ctx).edit().putString(KEY_ID, id).apply();
        }

        return id;
    }
}
