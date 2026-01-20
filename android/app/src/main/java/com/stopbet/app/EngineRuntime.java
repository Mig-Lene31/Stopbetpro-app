package com.stopbet.app;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class EngineRuntime {

    public static boolean isRunning(Context ctx) {
        return MotorStateStore.isRunning(ctx);
    }

    public static boolean isBlocked(Context ctx) {
        return EngineState.isBlocked(ctx);
    }

    public static void requestStart(Context ctx) {
        Intent i = new Intent(ctx, StopHeartService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ctx.startForegroundService(i);
        } else {
            ctx.startService(i);
        }
    }

    public static void requestStop(Context ctx) {
        ctx.stopService(new Intent(ctx, StopHeartService.class));
    }
}
