package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class LimitsState {

    private static final String PREF = "limits";
    private static final String WIN = "win";
    private static final String LOSS = "loss";

    private static SharedPreferences sp(Context c) {
        return c.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static float getWin(Context c) {
        return sp(c).getFloat(WIN, Float.MAX_VALUE);
    }

    public static float getLoss(Context c) {
        return sp(c).getFloat(LOSS, -Float.MAX_VALUE);
    }

    public static void set(Context c, float win, float loss) {
        sp(c).edit().putFloat(WIN, win).putFloat(LOSS, loss).apply();
    }
}
