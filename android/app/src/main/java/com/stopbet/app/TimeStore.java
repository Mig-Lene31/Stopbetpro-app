package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeStore {

    private static final String PREF = "time_store";
    private static final String KEY_MINUTES = "limit_minutes";

    public static void saveMinutes(Context ctx, int minutes) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putInt(KEY_MINUTES, minutes)
                .apply();
    }

    public static int getMinutes(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getInt(KEY_MINUTES, 0);
    }
}
