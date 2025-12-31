package com.stopbet.app;

import android.content.Context;

public class AuthState {

    private static final String PREF = "auth_state";
    private static final String KEY_UNLOCK_UNTIL = "unlock_until";

    public static boolean isUnlocked(Context ctx) {
        long until = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getLong(KEY_UNLOCK_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    // 🔁 MÉTODO ESPERADO PELO CÓDIGO ANTIGO
    public static boolean isAuthorized(Context ctx) {
        return isUnlocked(ctx);
    }

    public static void unlockFor30Days(Context ctx) {
        long until = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000);
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong(KEY_UNLOCK_UNTIL, until)
                .apply();
    }

    public static void clearStopBlock(Context ctx) {
        EngineState.clearBlock(ctx);
    }
}
