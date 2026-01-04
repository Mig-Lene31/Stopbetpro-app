package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String BLOCK_UNTIL = "block_until";
    private static final String BLOCK_REASON = "block_reason";

    // 🔁 REASONS LEGACY (NÃO QUEBRAR SWITCH / CASE)
    public static final int REASON_STOP_WIN  = 1;
    public static final int REASON_STOP_LOSS = 2;
    public static final int REASON_STOP_TIME = 3;
    public static final int REASON_MANUAL    = 4;

    // 🔹 API PRINCIPAL (NOVA)
    public static void blockFor12Hours(Context ctx, String reason) {
        long until = System.currentTimeMillis() + (12 * 60 * 60 * 1000L);
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit()
                .putLong(BLOCK_UNTIL, until)
                .putString(BLOCK_REASON, reason)
                .apply();
    }

    // 🔁 COMPATIBILIDADE TOTAL
    public static void blockFor12Hours(Context ctx) {
        blockFor12Hours(ctx, "AUTO");
    }

    public static void blockFor12Hours(Context ctx, int reason) {
        blockFor12Hours(ctx, mapReason(reason));
    }

    // 🔁 UTIL
    private static String mapReason(int r) {
        switch (r) {
            case REASON_STOP_WIN:
                return "STOP_WIN";
            case REASON_STOP_LOSS:
                return "STOP_LOSS";
            case REASON_STOP_TIME:
                return "STOP_TIME";
            case REASON_MANUAL:
                return "MANUAL";
            default:
                return "UNKNOWN";
        }
    }

    public static boolean isBlocked(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        long until = sp.getLong(BLOCK_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static long getRemainingTime(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        long until = sp.getLong(BLOCK_UNTIL, 0);
        long now = System.currentTimeMillis();
        return Math.max(0, until - now);
    }

    public static void autoUnlock(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit()
                .remove(BLOCK_UNTIL)
                .remove(BLOCK_REASON)
                .apply();
    }
}
