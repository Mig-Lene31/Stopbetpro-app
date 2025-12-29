package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String KEY_BLOCK_UNTIL = "blocked_until";

    private static SharedPreferences sp(Context c) {
        return c.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static boolean isBlocked(Context c) {
        long until = sp(c).getLong(KEY_BLOCK_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static void blockFor12Hours(Context c) {
        long until = System.currentTimeMillis() + (12L * 60 * 60 * 1000);
        sp(c).edit().putLong(KEY_BLOCK_UNTIL, until).apply();
    }

    public static long getRemainingTime(Context c) {
        long until = sp(c).getLong(KEY_BLOCK_UNTIL, 0);
        return Math.max(0, until - System.currentTimeMillis());
    }

    // 🔓 SOMENTE ADM
    public static void adminUnlock(Context c) {
        sp(c).edit().remove(KEY_BLOCK_UNTIL).apply();
        MotorState.forceDisable(c); // segurança obrigatória
    }
}
