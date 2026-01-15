package com.stopbet.app;

import android.content.Context;

public class EngineRunner {

    public static void run(Context ctx, double balance) {
        StopLimitChecker.check(ctx, balance);
    }
}
