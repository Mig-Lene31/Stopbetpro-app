package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String KEY_BLOCKED_UNTIL = "blocked_until";

    // ===============================
    // BLOQUEIO ATIVO?
    // ===============================
    public static boolean isBlocked(Context ctx) {
        long until = getBlockedUntil(ctx);
        return System.currentTimeMillis() < until;
    }

    // ===============================
    // TEMPO RESTANTE DE BLOQUEIO
    // ===============================
    public static long getRemainingTime(Context ctx) {
        long until = getBlockedUntil(ctx);
        long now = System.currentTimeMillis();
        return Math.max(0, until - now);
    }

    // ===============================
    // BLOQUEIO PADRÃO DE 12H
    // ===============================
    public static void blockFor12Hours(Context ctx) {
        long until = System.currentTimeMillis() + (12L * 60 * 60 * 1000);
        setBlockedUntil(ctx, until);
    }

    // ===============================
    // 🔓 ADM: LIBERAR POR 30 DIAS
    // ===============================
    public static void unlockFor30Days(Context ctx) {
        long until = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000);
        setBlockedUntil(ctx, until);
    }

    // ===============================
    // 🔓 ADM: QUEBRAR BLOQUEIO ANTES DAS 12H
    // ===============================
    public static void clearBlock(Context ctx) {
        setBlockedUntil(ctx, 0);
    }

    // ===============================
    // AUTO DESBLOQUEIO
    // ===============================
    public static void autoUnlock(Context ctx) {
        if (!isBlocked(ctx)) {
            clearBlock(ctx);
        }
    }

    // ===============================
    // STORAGE
    // ===============================
    private static void setBlockedUntil(Context ctx, long value) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_BLOCKED_UNTIL, value)
                .apply();
    }

    private static long getBlockedUntil(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_BLOCKED_UNTIL, 0);
    }
}
