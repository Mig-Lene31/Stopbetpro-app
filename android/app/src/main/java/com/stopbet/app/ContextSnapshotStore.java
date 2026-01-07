package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextSnapshotStore {

    private static final String PREF = "context_snapshot";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String W = "w";
    private static final String H = "h";

    public static void save(Context ctx, int x, int y, int w, int h) {
        sp(ctx).edit()
                .putInt(X, x)
                .putInt(Y, y)
                .putInt(W, w)
                .putInt(H, h)
                .apply();
    }

    public static boolean has(Context ctx) {
        return sp(ctx).contains(X);
    }

    public static int[] get(Context ctx) {
        return new int[]{
                sp(ctx).getInt(X, -1),
                sp(ctx).getInt(Y, -1),
                sp(ctx).getInt(W, -1),
                sp(ctx).getInt(H, -1)
        };
    }

    public static void clear(Context ctx) {
        sp(ctx).edit().clear().apply();
    }

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
}
