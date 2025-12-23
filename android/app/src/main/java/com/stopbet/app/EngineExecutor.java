package com.stopbet.app;

import android.content.Context;

public class EngineExecutor {

    public static void handleDecision(Context ctx, boolean shouldBlock) {

        if (!shouldBlock) return;

        if (EngineConfig.ENGINE_TEST_MODE) {
            // MODO TESTE
            AppState.block12h(ctx);
        } else {
            // MODO FINAL (VPN / Accessibility)
            AppState.block12h(ctx);
        }
    }
}
