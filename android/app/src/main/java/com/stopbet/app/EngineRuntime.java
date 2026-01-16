package com.stopbet.app;

import android.content.Context;

public class EngineRuntime {

    public static boolean isRunning(Context ctx) {
        return MotorStateStore.isActive(ctx);
    }

    public static void start(Context ctx) {
        MotorStateStore.activate(ctx);
        ctx.startService(new android.content.Intent(ctx, StopHeartService.class));
    }

    public static void stop(Context ctx) {
        MotorStateStore.deactivate(ctx);
        EngineState.clearBlock(ctx);
        ctx.stopService(new android.content.Intent(ctx, StopHeartService.class));
        ctx.stopService(new android.content.Intent(ctx, BetBlockVpnService.class));
    }

    public static boolean isBlocked(Context ctx) {
        return EngineState.isBlocked(ctx);
    }

    public static void block(Context ctx) {
        EngineState.blockFor12Hours(ctx);
    }
}
