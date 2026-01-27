package com.stopbet.app;

import android.content.Context;

public class AppPrefs {

    public static void setWin(Context ctx, String value) {
        try {
            LimitsStore.setWin(ctx, Float.parseFloat(value));
        } catch (Exception ignored) {}
    }

    public static void setLoss(Context ctx, String value) {
        try {
            LimitsStore.setLoss(ctx, Float.parseFloat(value));
        } catch (Exception ignored) {}
    }

    public static String getWin(Context ctx) {
        return String.valueOf(LimitsStore.getWin(ctx));
    }

    public static float getWinValue(Context ctx) {
        return LimitsStore.getWin(ctx);
    }

    public static String getLoss(Context ctx) {
        return String.valueOf(LimitsStore.getLoss(ctx));
    }

    public static float getLossValue(Context ctx) {
        return LimitsStore.getLoss(ctx);
    }
}
