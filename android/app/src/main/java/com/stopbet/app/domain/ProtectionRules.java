package com.stopbet.app.domain;

public class ProtectionRules {

    public static ProtectionDecision evaluate(ProtectionInput in) {

        if (in.blockUntil > in.now) {
            return ProtectionDecision.KEEP_BLOCKED;
        }

        if (in.blockUntil > 0 && in.blockUntil <= in.now) {
            return ProtectionDecision.RELEASE;
        }

        if (in.stopLoss > 0 && in.balance <= (in.deposit - in.stopLoss)) {
            return ProtectionDecision.BLOCK_12H;
        }

        if (in.stopWin > 0 && in.balance >= (in.deposit + in.stopWin)) {
            return ProtectionDecision.BLOCK_12H;
        }

        if (in.maxMinutes > 0) {
            long elapsed = (in.now - in.sessionStart) / 60000L;
            if (elapsed >= in.maxMinutes) {
                return ProtectionDecision.BLOCK_12H;
            }
        }

        return ProtectionDecision.CONTINUE;
    }
}
