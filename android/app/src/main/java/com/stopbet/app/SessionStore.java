package com.stopbet.app;

import android.content.Context;

public class SessionStore {

    private static final String PREF = "session_store";
    private static final String KEY_START = "start";

    public static void start(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_START, System.currentTimeMillis())
                .apply();
    }

    public static long getStart(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_START, 0);
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
