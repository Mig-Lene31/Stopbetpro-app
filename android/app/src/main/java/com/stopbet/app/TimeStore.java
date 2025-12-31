package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeStore {

    private static final String PREF = "time_store";
    private static final String KEY_LIMIT = "limit_minutes";
    private static final String KEY_USED = "used_minutes";
    private static final String KEY_DAY = "day_key";

    // 🔑 MÉTODO QUE O BUILD ESTÁ COBRANDO
    public static boolean hasTimeLimit(Context ctx) {
        return getMinutes(ctx) > 0;
    }

    public static int getMinutes(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getInt(KEY_LIMIT, 0);
    }

    public static void setMinutes(Context ctx, int minutes) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putInt(KEY_LIMIT, minutes)
                .apply();
    }

    public static int getUsedMinutesToday(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        long today = System.currentTimeMillis() / (24 * 60 * 60 * 1000);
        long savedDay = prefs.getLong(KEY_DAY, -1);

        if (today != savedDay) {
            prefs.edit()
                    .putLong(KEY_DAY, today)
                    .putInt(KEY_USED, 0)
                    .apply();
            return 0;
        }

        return prefs.getInt(KEY_USED, 0);
    }

    public static void addMinute(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        int used = getUsedMinutesToday(ctx);
        prefs.edit().putInt(KEY_USED, used + 1).apply();
    }
}
