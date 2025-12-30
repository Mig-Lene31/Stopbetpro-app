package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeStore {

    private static final String PREF = "stopbet_time";
    private static final String KEY_ACTIVE = "time_active";
    private static final String KEY_MINUTES = "time_minutes";

    // ===== CONTROLE DE ATIVAÇÃO =====
    public static void setActive(Context ctx, boolean active) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_ACTIVE, active)
                .apply();
    }

    public static boolean isActive(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY_ACTIVE, false);
    }

    // ===== TEMPO CONFIGURADO =====
    public static void setMinutes(Context ctx, int minutes) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putInt(KEY_MINUTES, minutes)
                .apply();
    }

    public static int getMinutes(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getInt(KEY_MINUTES, 0);
    }

    public static boolean hasTimeLimit(Context ctx) {
        return getMinutes(ctx) > 0;
    }

    // ===== RESET =====
    public static void clear(Context ctx) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
