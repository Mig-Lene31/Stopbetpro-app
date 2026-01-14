package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class BalanceConfirmationStore {

    private static final String PREF = "balance_confirm";
    private static final String CONFIRMED = "confirmed";
    private static final String TRIES = "tries";

    public static void confirm(Context ctx) {
        sp(ctx).edit().putBoolean(CONFIRMED, true).apply();
    }

    public static boolean isConfirmed(Context ctx) {
        return sp(ctx).getBoolean(CONFIRMED, false);
    }

    public static int incTry(Context ctx) {
        int t = sp(ctx).getInt(TRIES, 0) + 1;
        sp(ctx).edit().putInt(TRIES, t).apply();
        return t;
    }

    public static int getTries(Context ctx) {
        return sp(ctx).getInt(TRIES, 0);
    }

    public static void reset(Context ctx) {
        sp(ctx).edit().clear().apply();
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
