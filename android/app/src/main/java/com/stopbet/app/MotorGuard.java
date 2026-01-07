package com.stopbet.app;

import android.content.Context;

public class MotorGuard {

    public static boolean canRun(Context ctx) {

        if (!MotorState.isEnabled(ctx)) return false;

        if (!DepositStore.hasValue(ctx)) {
            MotorState.forceDisable(ctx);
            return false;
        }

        boolean hasLimits = LimitsStore.hasLimits(ctx);
        boolean hasTime = TimeStore.hasTime(ctx);

        if (hasLimits && hasTime) {
            MotorState.forceDisable(ctx);
            return false;
        }

        if (!hasLimits && !hasTime) {
            MotorState.forceDisable(ctx);
            return false;
        }

        if (LimitsStore.hasLimits(ctx) && !BalanceConfirmationStore.isConfirmed(ctx)) return false;
        return true;
    }
}
