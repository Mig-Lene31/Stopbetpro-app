package com.stopbet.app;

import android.content.Context;

public class InterventionCooldownStore {

    private static final String PREF = "intervention_cooldown";
    private static final String KEY_UNTIL = "cooldown_until";

    public static void start(Context ctx, long millis) {
        long until = System.currentTimeMillis() + millis;
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_UNTIL, until)
                .apply();
    }

    public static boolean isActive(Context ctx) {
        long until = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }
}
