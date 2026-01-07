package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineCooldownStore {

    private static final String PREF = "engine_cooldown";
    private static final String LAST_RUN = "last_run";

    private static final long COOLDOWN_MS = 15000;

    public static boolean canRun(Context ctx) {
        long now = System.currentTimeMillis();
        long last = sp(ctx).getLong(LAST_RUN, 0);
        return (now - last) > COOLDOWN_MS;
    }

    public static void markRun(Context ctx) {
        sp(ctx).edit()
            .putLong(LAST_RUN, System.currentTimeMillis())
            .apply();
    }

    public static void clear(Context ctx) {
        sp(ctx).edit().clear().apply();
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
