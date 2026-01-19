package com.stopbet.app.runtime;

import android.content.Context;
import com.stopbet.app.*;
import com.stopbet.app.domain.*;

public class RuntimeHeartbeat {

    public static void tick(Context ctx, double balance) {

        ProtectionInput in = new ProtectionInput();

        in.deposit = DepositStore.get(ctx);
        in.balance = balance;
        in.stopWin = LimitsStore.getWin(ctx);
        in.stopLoss = LimitsStore.getLoss(ctx);
        in.sessionStart = SessionStore.getStart(ctx);
        in.blockUntil = EngineState.getBlockUntil(ctx);
        in.maxMinutes = TimeStore.getMinutes(ctx);
        in.now = System.currentTimeMillis();

        ProtectionDecision decision =
                ProtectionRules.evaluate(in);

        switch (decision) {
            case BLOCK_12H:
                EngineState.blockFor12Hours(ctx);
                break;

            case RELEASE:
                EngineState.clearBlock(ctx);
                break;

            case KEEP_BLOCKED:
            case CONTINUE:
            default:
                break;
        }
    }
}
