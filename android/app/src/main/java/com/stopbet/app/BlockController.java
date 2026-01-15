package com.stopbet.app;

import android.content.Context;

public class BlockController {

    public static void triggerBlock(Context ctx) {
        EngineState.blockFor12Hours(ctx);
    }

    public static boolean isBlocked(Context ctx) {
        return EngineState.isBlocked(ctx);
    }
}
