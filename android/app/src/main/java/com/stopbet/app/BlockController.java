package com.stopbet.app;

import android.content.Context;
import android.content.Intent;

public class BlockController {

    private static final long COOLDOWN_MS = 60_000;

    public static void interveneApp(Context ctx, String pkg) {

        if (InterventionCooldownStore.isActive(ctx)) return;
        InterventionCooldownStore.start(ctx, COOLDOWN_MS);

        Intent i = new Intent(ctx, BlockScreenActivity.class);
        i.putExtra(
                "reason",
                InterventionMessageEngine.messageForApp(pkg)
        );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }

    public static void interveneDomain(Context ctx, String domain) {

        if (InterventionCooldownStore.isActive(ctx)) return;
        InterventionCooldownStore.start(ctx, COOLDOWN_MS);

        Intent i = new Intent(ctx, BlockScreenActivity.class);
        i.putExtra(
                "reason",
                InterventionMessageEngine.messageForDomain(domain)
        );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }
}
