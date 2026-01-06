package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class UnlockStore {

    private static final String PREF = "unlock_store";
    private static final String KEY_UNTIL = "unlocked_until";

    public static void unlockForDays(Context ctx, int days) {
        long until = System.currentTimeMillis() + (days * 24L * 60L * 60L * 1000L);
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_UNTIL, until)
                .apply();
    }

    public static boolean isUnlocked(Context ctx) {
        long until = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
