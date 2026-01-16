package com.stopbet.app;

import android.content.Context;
import android.util.Log;

public class RuntimeLog {

    private static final String TAG = "KAIROS_RUNTIME";

    public static void log(Context ctx, String msg) {
        Log.d(TAG, msg);
    }
}
