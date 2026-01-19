package com.stopbet.app;

import android.content.Context;
import android.content.Intent;

public class BlockController {

    public static void triggerBlock(Context ctx) {
        EngineState.blockFor12Hours(ctx);
        ctx.startService(new Intent(ctx, BetBlockVpnService.class));
    }

    public static boolean isBlocked(Context ctx) {
        return EngineState.isBlocked(ctx);
    }
}
