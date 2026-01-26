package com.stopbet.app;

import android.content.Context;
import android.widget.Toast;

public class Toasts {

    public static void error(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
