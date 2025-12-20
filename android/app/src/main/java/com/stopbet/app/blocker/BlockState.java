package com.stopbet.app.blocker;

import android.content.Context;
import android.content.SharedPreferences;

public class BlockState {

    private static final String PREF = "STOPBET_BLOCK";
    private static final String KEY_END = "BLOCK_END";

    public static void startBlock(Context ctx, long durationSeconds) {
        long endTime = System.currentTimeMillis() + (durationSeconds * 1000);
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putLong(KEY_END, endTime).apply();
    }

    public static boolean isBlocked(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        long end = sp.getLong(KEY_END, 0);
        return System.currentTimeMillis() < end;
    }

    public static long remainingSeconds(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        long end = sp.getLong(KEY_END, 0);
        long diff = (end - System.currentTimeMillis()) / 1000;
        return Math.max(diff, 0);
    }

    // 💰 DESBLOQUEIO PAGO
    public static void clear(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().remove(KEY_END).apply();
    }
}
