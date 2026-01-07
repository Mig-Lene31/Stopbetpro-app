package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class BalanceConfirmState {

    private static final String PREF = "balance_confirm_state";
    private static final String CONFIRMED = "confirmed";
    private static final String TRIES = "tries";

    public static boolean isConfirmed(Context ctx) {
        return sp(ctx).getBoolean(CONFIRMED, false);
    }

    public static void confirm(Context ctx) {
        sp(ctx).edit()
            .putBoolean(CONFIRMED, true)
            .putInt(TRIES, 0)
            .apply();
    }

    public static int incTry(Context ctx) {
        int t = sp(ctx).getInt(TRIES, 0) + 1;
        sp(ctx).edit().putInt(TRIES, t).apply();
        return t;
    }

    public static void clear(Context ctx) {
        sp(ctx).edit().clear().apply();
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
