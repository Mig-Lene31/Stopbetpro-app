package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class DailyTimeEngine {

    private static final String PREF = "daily_time";
    private static final String KEY_USED = "used_minutes";
    private static final int MAX_MINUTES = 60; // padrão, sobrescrito pelo TimeActivity

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static void addMinute(Context ctx) {
        int used = sp(ctx).getInt(KEY_USED, 0);
        sp(ctx).edit().putInt(KEY_USED, used + 1).apply();
    }

    public static boolean exceeded(Context ctx) {
        int limit = TimeStore.getMinutes(ctx);
        if (limit <= 0) return false;
        int used = sp(ctx).getInt(KEY_USED, 0);
        return used >= limit;
    }

    public static void reset(Context ctx) {
        sp(ctx).edit().clear().apply();
    }
}
