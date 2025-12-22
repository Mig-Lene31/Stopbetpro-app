package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppStorage {

    private static final String PREFS = "stopbet_prefs";

    /* ===== USER ID ===== */

    public static String getUserId(Context c) {
        SharedPreferences p = c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String id = p.getString("user_id", null);

        if (id == null) {
            id = "SBP-" + System.currentTimeMillis();
            p.edit().putString("user_id", id).apply();
        }
        return id;
    }

    /* ===== ATIVAÇÃO ===== */

    public static void activateMonthly(Context c) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putLong("active_until", -1)
                .apply();
    }

    public static void activate12h(Context c) {
        long until = System.currentTimeMillis() + (12L * 60 * 60 * 1000);
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putLong("active_until", until)
                .apply();
    }

    public static void block(Context c) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .remove("active_until")
                .apply();
    }

    public static boolean isActive(Context c) {
        SharedPreferences p = c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        long until = p.getLong("active_until", 0);

        if (until == -1) return true;
        if (until == 0) return false;

        return System.currentTimeMillis() < until;
    }
}
