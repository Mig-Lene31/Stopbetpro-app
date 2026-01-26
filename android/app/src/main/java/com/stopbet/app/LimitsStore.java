package com.stopbet.app;

import android.content.Context;

public class LimitsStore {

    public static double getWin(Context ctx) {
        return ctx.getSharedPreferences("limits", Context.MODE_PRIVATE)
                .getFloat("win", 0f);
    }

    public static double getLoss(Context ctx) {
        return ctx.getSharedPreferences("limits", Context.MODE_PRIVATE)
                .getFloat("loss", 0f);
    }

    public static boolean isConfigured(Context ctx) {
        return getWin(ctx) > 0 && getLoss(ctx) > 0;
    }
}
