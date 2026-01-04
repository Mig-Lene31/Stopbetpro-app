package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String BLOCK_UNTIL = "block_until";
    private static final String BLOCK_REASON = "block_reason";

    public static void blockFor12Hours(Context ctx, String reason) {
        long until = System.currentTimeMillis() + (12 * 60 * 60 * 1000L);
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit()
                .putLong(BLOCK_UNTIL, until)
                .putString(BLOCK_REASON, reason)
                .apply();
    }

    // 🔁 COMPATIBILIDADE (API ANTIGA)
    public static void blockFor12Hours(Context ctx) {
        blockFor12Hours(ctx, "AUTO");
    }

    public static boolean isBlocked(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        long until = sp.getLong(BLOCK_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    // 🔁 MÉTODOS QUE ESTAVAM FALTANDO
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
