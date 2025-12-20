package com.stopbet.app.blocker;

import android.content.Context;
import android.content.Intent;

import com.stopbet.app.BlockActivity;
import com.stopbet.app.rules.RuleEngine;

public class BlockController {

    private static boolean enabled = false;
    private static Context appContext;

    private static final long BLOCK_TIME_SECONDS = 43200; // 12h

    public static void init(Context context) {
        appContext = context.getApplicationContext();
    }

    public static void setEnabled(boolean value) {
        enabled = value;
        if (!value) {
            RuleEngine.reset();
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void startRules(
            int maxTimeSeconds,
            double initialValue,
            double stopWin,
            double stopLoss
    ) {
        RuleEngine.start(maxTimeSeconds, initialValue, stopWin, stopLoss);
    }

    public static void updateValue(double value) {
        if (!enabled) return;

        if (RuleEngine.check(value)) {
            BlockState.startBlock(appContext, BLOCK_TIME_SECONDS);
            forceBlock(appContext, "RULE_TRIGGERED");
        }
    }

    public static boolean isBlocked() {
        return BlockState.isBlocked(appContext);
    }

    // 💰 DESBLOQUEIO APÓS PAGAMENTO
    public static void unlockPaid() {
        BlockState.clear(appContext);
        enabled = false;
        RuleEngine.reset();
    }

    public static void forceBlock(Context context, String reason) {
        Intent intent = new Intent(context, BlockActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("REASON", reason);
        context.startActivity(intent);
    }
}
