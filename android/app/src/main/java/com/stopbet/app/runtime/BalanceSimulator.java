package com.stopbet.app.runtime;

import android.content.Context;
import com.stopbet.app.AppState;

public class BalanceSimulator {

    public static void simulate(Context ctx, double delta) {
        double current = AppState.getBalance(ctx);
        AppState.setBalance(ctx, current + delta);
    }
}
