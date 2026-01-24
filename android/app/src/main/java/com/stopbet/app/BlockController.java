package com.stopbet.app;

import android.content.Context;
import android.content.Intent;

public class BlockController {

    private static final long COOLDOWN_MS = 60_000; // 1 minuto

    public static void intervene(Context ctx, String reason) {

        if (InterventionCooldownStore.isActive(ctx)) return;

        InterventionCooldownStore.start(ctx, COOLDOWN_MS);

        Intent i = new Intent(ctx, BlockScreenActivity.class);
        i.putExtra("reason", reason);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }
}
