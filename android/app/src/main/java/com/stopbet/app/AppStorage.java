package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppStorage {

    private static final String PREFS = "stopbet_prefs";

    /* ===== PAGAMENTO ===== */

    public static void activateMonthly(Context c) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putLong("active_until", -1) // ilimitado
                .apply();
    }

    public static void activateTemporary(Context c) {
        long now = System.currentTimeMillis();
        long twelveHours = 12L * 60 * 60 * 1000;
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putLong("active_until", now + twelveHours)
                .apply();
    }

    public static boolean isActive(Context c) {
        SharedPreferences p =
                c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        long until = p.getLong("active_until", 0);

        if (until == -1) return true; // mensal
        if (until == 0) return false;

        return System.currentTimeMillis() < until;
    }

    /* ===== CONFIGURAÇÕES ===== */

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

    public static void saveDeposit(Context c, float deposit) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putFloat("deposit", deposit)
                .apply();
    }

    public static boolean isFullyConfigured(Context c) {
        SharedPreferences p =
                c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        return isActive(c)
                && p.contains("time_minutes")
                && p.contains("stop_win")
                && p.contains("stop_loss")
                && p.contains("deposit");
    }

    public static int getTime(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getInt("time_minutes", 0);
    }

    public static float getWin(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getFloat("stop_win", 0f);
    }

    public static float getLoss(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getFloat("stop_loss", 0f);
    }

    public static float getDeposit(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getFloat("deposit", 0f);
    }
}
