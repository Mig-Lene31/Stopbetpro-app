package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class FinanceEngineState {

    private static final String PREF = "finance_engine_state";
    private static final String PENDING = "pending_confirm";

    public static boolean isPending(Context ctx) {
        return sp(ctx).getBoolean(PENDING, false);
    }

    public static void setPending(Context ctx, boolean v) {
        sp(ctx).edit().putBoolean(PENDING, v).apply();
    }

    public static void clear(Context ctx) {
        sp(ctx).edit().clear().apply();
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
