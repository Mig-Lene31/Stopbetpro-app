package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppStorage {

    private static final String PREFS = "stopbet_prefs";

    public static void saveTime(Context c, int minutes) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putInt("time_minutes", minutes)
                .apply();
    }

    public static void saveLimits(Context c, float win, float loss) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putFloat("stop_win", win)
                .putFloat("stop_loss", loss)
                .apply();
    }

    public static boolean isConfigured(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .contains("time_minutes");
    }
}
