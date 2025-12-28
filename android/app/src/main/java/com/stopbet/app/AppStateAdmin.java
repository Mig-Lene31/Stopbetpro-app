package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppStateAdmin {

    private static final String PREFS = "admin_state";
    private static final String KEY_RELEASE_UNTIL = "release_until";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void releaseFor30Days(Context ctx) {
        long until = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000);
        sp(ctx).edit().putLong(KEY_RELEASE_UNTIL, until).apply();
    }

    public static boolean isReleased(Context ctx) {
        long until = sp(ctx).getLong(KEY_RELEASE_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static void revoke(Context ctx) {
        sp(ctx).edit().remove(KEY_RELEASE_UNTIL).apply();
    }
}
