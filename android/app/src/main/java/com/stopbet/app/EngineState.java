package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;

public class EngineState {

    private static final String PREF = "engine_state";
    private static final String KEY_BLOCK_UNTIL = "blocked_until";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static boolean isBlocked(Context ctx) {
        long until = sp(ctx).getLong(KEY_BLOCK_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static void blockFor12Hours(Context ctx) {
        long until = System.currentTimeMillis() + (12L * 60 * 60 * 1000);
        sp(ctx).edit().putLong(KEY_BLOCK_UNTIL, until).apply();
        MotorState.forceDisable(ctx);

        ctx.startService(new Intent(ctx, BetBlockVpnService.class));
    }

    public static long getRemainingTime(Context ctx) {
        long until = sp(ctx).getLong(KEY_BLOCK_UNTIL, 0);
        return Math.max(0, until - System.currentTimeMillis());
    }

    public static void autoUnlock(Context ctx) {
        sp(ctx).edit().remove(KEY_BLOCK_UNTIL).apply();
        MotorState.forceDisable(ctx);

        ctx.stopService(new Intent(ctx, BetBlockVpnService.class));
    }

    public static void adminUnlock(Context ctx) {
        sp(ctx).edit().remove(KEY_BLOCK_UNTIL).apply();
        MotorState.forceDisable(ctx);

        ctx.stopService(new Intent(ctx, BetBlockVpnService.class));
    }
}
