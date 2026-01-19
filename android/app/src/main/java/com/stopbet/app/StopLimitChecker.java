package com.stopbet.app;

import android.content.Context;
import com.stopbet.app.runtime.DomainBridge;

public class StopLimitChecker {

    public static void check(Context ctx, double currentBalance) {
        DomainBridge.evaluate(ctx, currentBalance);
    }
}
