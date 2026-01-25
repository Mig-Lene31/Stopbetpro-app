package com.stopbet.app.runtime;

import android.content.Context;
import com.stopbet.app.domain.*;
import com.stopbet.app.*;

public class DomainBridge {

    public static void evaluate(Context ctx, double balance) {

        // Se já houve Stop hoje, mantém bloqueado
        if (DailyStopStore.isStoppedToday(ctx)) {
            EngineState.blockFor12Hours(ctx);
            return;
        }

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
                EngineRuntimeCore.check(ctx, in);

        switch (decision) {

            case BLOCK_12H:
                DailyStopStore.mark(ctx, "STOP");
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
