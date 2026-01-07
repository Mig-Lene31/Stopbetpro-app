package com.stopbet.app;

import android.content.Context;

public class BlockController {

    public static void block(Context ctx, int reason) {
        EngineState.blockFor12Hours(ctx, reason);
        VpnController.start(ctx);
    }

    public static void unblock(Context ctx) {
        EngineState.clearBlock(ctx);
        VpnController.stop(ctx);
    }
}
