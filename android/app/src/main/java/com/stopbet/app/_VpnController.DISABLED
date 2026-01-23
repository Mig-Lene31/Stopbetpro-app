package com.stopbet.app;

import android.content.Context;
import android.content.Intent;

public class VpnController {

    public static void start(Context ctx) {
        ctx.startService(new Intent(ctx, BetBlockVpnService.class));
    }

    public static void stop(Context ctx) {
        ctx.stopService(new Intent(ctx, BetBlockVpnService.class));
    }
}
