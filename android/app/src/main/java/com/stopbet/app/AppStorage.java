package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppStorage {

    private static final String PREFS = "stopbet_prefs";

    // =========================
    // USER ID
    // =========================
    public static String getUserId(Context c) {
        SharedPreferences p = c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String id = p.getString("user_id", null);

        if (id == null) {
            id = "SBP-" + System.currentTimeMillis();
            p.edit().putString("user_id", id).apply();
        }
        return id;
    }

    // =========================
    // ATIVAÇÃO
    // =========================
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

    // =========================
    // DEPÓSITO
    // =========================
    public static void saveDeposit(Context c, float value) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putFloat("deposit", value)
                .apply();
    }

    public static float getDeposit(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getFloat("deposit", 0f);
    }

    // =========================
    // TEMPO
    // =========================
    public static void saveTime(Context c, int minutes) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putInt("time_minutes", minutes)
                .apply();
    }

    public static int getTime(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getInt("time_minutes", 0);
    }

    // =========================
    // LIMITES
    // =========================
    public static void saveLimits(Context c, float win, float loss) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putFloat("stop_win", win)
                .putFloat("stop_loss", loss)
                .apply();
    }

    public static float getWin(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getFloat("stop_win", 0f);
    }

    public static float getLoss(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getFloat("stop_loss", 0f);
    }
}
