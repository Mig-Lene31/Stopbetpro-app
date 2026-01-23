package com.stopbet.app;

import android.content.Context;

public class BlockController {

    public static void block(Context ctx) {
        // BLOQUEIO AGORA É FEITO VIA:
        // - Accessibility
        // - KairosVpnService
        // Este método fica como ponto lógico central
        MotorStateStore.setRunning(ctx, false);
    }
}
