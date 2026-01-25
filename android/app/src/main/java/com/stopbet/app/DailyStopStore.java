package com.stopbet.app;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DailyStopStore {

    private static final String PREF = "daily_stop";
    private static final String KEY_DATE = "date";
    private static final String KEY_TYPE = "type";

    private static String today() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US)
                .format(new Date());
    }

    public static void mark(Context ctx, String type) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_DATE, today())
                .putString(KEY_TYPE, type)
                .apply();
    }

    public static boolean isStoppedToday(Context ctx) {
        String date = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getString(KEY_DATE, null);
        return today().equals(date);
    }

    public static String getType(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getString(KEY_TYPE, null);
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
