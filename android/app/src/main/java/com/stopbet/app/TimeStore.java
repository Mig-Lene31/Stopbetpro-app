package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeStore {

    private static final String PREF = "time_store";
    private static final String MINUTES = "minutes";

    public static void setMinutes(Context ctx, int m) {
        sp(ctx).edit().putInt(MINUTES, m).apply();
    }

    public static int getMinutes(Context ctx) {
        return sp(ctx).getInt(MINUTES, 0);
    }

    public static boolean hasTime(Context ctx) {
        return getMinutes(ctx) > 0;
    }

    public static boolean hasTimeLimit(Context ctx) {
        return hasTime(ctx);
    }

    public static int getUsedMinutesToday(Context ctx) {
        return 0;
    }

    public static void clear(Context ctx) {
        sp(ctx).edit().clear().apply();
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
