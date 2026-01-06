package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AdminUnlockStore {

    private static final String PREF = "admin_unlocks";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static void unlockForMillis(Context ctx, String userId, long millis) {
        long until = System.currentTimeMillis() + millis;
        sp(ctx).edit().putLong(userId, until).apply();
    }

    public static boolean isUnlocked(Context ctx, String userId) {
        long until = sp(ctx).getLong(userId, 0);
        return System.currentTimeMillis() < until;
    }

    public static long getRemaining(Context ctx, String userId) {
        long until = sp(ctx).getLong(userId, 0);
        return Math.max(0, until - System.currentTimeMillis());
    }
}
