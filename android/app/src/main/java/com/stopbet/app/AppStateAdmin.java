package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppStateAdmin {

    private static final String PREFS = "admin_state";
    private static final String KEY_RELEASED = "released";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    // Libera ou bloqueia o acesso ao app (avançar)
    public static void setReleased(Context ctx, boolean value) {
        sp(ctx).edit().putBoolean(KEY_RELEASED, value).apply();
    }

    // Verifica se o acesso está liberado
    public static boolean isReleased(Context ctx) {
        return sp(ctx).getBoolean(KEY_RELEASED, false);
    }
}
