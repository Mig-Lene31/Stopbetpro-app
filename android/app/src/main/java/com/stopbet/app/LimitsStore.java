package com.stopbet.app;

import android.content.Context;
import android.content.SharedPreferences;

public class LimitsStore {

    private static final String PREF = "limits_store";
    private static final String KEY_WIN = "stop_win";
    private static final String KEY_LOSS = "stop_loss";

    public static float getWin(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getFloat(KEY_WIN, 0f);
    }

    public static float getLoss(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getFloat(KEY_LOSS, 0f);
    }
}
