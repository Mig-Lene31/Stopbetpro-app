package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class AppState {

    private static final String PREFS = "stopbet_state";

    // ===== USER =====
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_RELEASED = "released";

    // ===== VALUES =====
    private static final String KEY_DEPOSIT = "deposit";
    private static final String KEY_STOP_WIN = "stop_win";
    private static final String KEY_STOP_LOSS = "stop_loss";
    private static final String KEY_TIME_LIMIT = "time_limit";

    // ===== BLOCK =====
    private static final String KEY_BLOCK_UNTIL = "block_until";

    // ===== ENGINES =====
    private static final String KEY_ENGINE_ENABLED = "engine_enabled";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    // ===== USER =====
    public static String getUserId(Context ctx) {
        String id = sp(ctx).getString(KEY_USER_ID, null);
        if (id == null) {
            id = UUID.randomUUID().toString().substring(0,8).toUpperCase();
            sp(ctx).edit().putString(KEY_USER_ID, id).apply();
        }
        return id;
    }

    public static boolean isReleased(Context ctx) {
        return sp(ctx).getBoolean(KEY_RELEASED, false);
    }

    public static void setReleased(Context ctx, boolean v) {
        sp(ctx).edit().putBoolean(KEY_RELEASED, v).apply();
    }

    // ===== VALUES =====
    public static void setDeposit(Context ctx, float v) {
        sp(ctx).edit().putFloat(KEY_DEPOSIT, v).apply();
    }

    public static float getDeposit(Context ctx) {
        return sp(ctx).getFloat(KEY_DEPOSIT, 0f);
    }

    public static void setStopWin(Context ctx, float v) {
        sp(ctx).edit().putFloat(KEY_STOP_WIN, v).apply();
    }

    public static float getStopWin(Context ctx) {
        return sp(ctx).getFloat(KEY_STOP_WIN, 0f);
    }

    public static void setStopLoss(Context ctx, float v) {
        sp(ctx).edit().putFloat(KEY_STOP_LOSS, v).apply();
    }

    public static float getStopLoss(Context ctx) {
        return sp(ctx).getFloat(KEY_STOP_LOSS, 0f);
    }

    public static void setTimeLimit(Context ctx, int m) {
        sp(ctx).edit().putInt(KEY_TIME_LIMIT, m).apply();
    }

    public static int getTimeLimit(Context ctx) {
        return sp(ctx).getInt(KEY_TIME_LIMIT, 0);
    }

    // ===== BLOCK 12H =====
    public static void block12h(Context ctx) {
        long until = System.currentTimeMillis() + (12 * 60 * 60 * 1000);
        sp(ctx).edit().putLong(KEY_BLOCK_UNTIL, until).apply();
    }

    public static boolean isBlocked(Context ctx) {
        long until = sp(ctx).getLong(KEY_BLOCK_UNTIL, 0);
        return System.currentTimeMillis() < until;
    }

    public static void clearBlock(Context ctx) {
        sp(ctx).edit().remove(KEY_BLOCK_UNTIL).apply();
    }

    // ===== ENGINE =====
    public static void setEngineEnabled(Context ctx, boolean v) {
        sp(ctx).edit().putBoolean(KEY_ENGINE_ENABLED, v).apply();
    }

    public static boolean isEngineEnabled(Context ctx) {
        return sp(ctx).getBoolean(KEY_ENGINE_ENABLED, false);
    }
}
