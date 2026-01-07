package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class MotorState {

    private static final String PREF = "motor_state";
    private static final String KEY = "enabled";

    public static boolean isEnabled(Context c) {
        return c.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getBoolean(KEY, false);
    }

    public static void setEnabled(Context c, boolean v) {
        c.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY, v)
                .apply();
    }

    public static void forceDisable(Context c) {
        setEnabled(c, false);
    }
}

    public static void enable(Context ctx) {
        setEnabled(ctx, true);
    }


    public static void enable(Context ctx) {
        setEnabled(ctx, true);
    }

