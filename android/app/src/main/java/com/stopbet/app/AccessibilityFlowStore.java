package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AccessibilityFlowStore {

    private static final String PREF = "kairos_accessibility_flow";
    private static final String KEY = "started";

    public static void markStarted(Context ctx) {
        prefs(ctx).edit().putBoolean(KEY, true).apply();
    }

    public static boolean hasStarted(Context ctx) {
        return prefs(ctx).getBoolean(KEY, false);
    }

    public static void clear(Context ctx) {
        prefs(ctx).edit().remove(KEY).apply();
    }

    private static SharedPreferences prefs(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
