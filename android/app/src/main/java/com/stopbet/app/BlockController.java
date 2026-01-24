package com.stopbet.app;

import android.content.Context;
import android.content.Intent;

public class BlockController {

    public static void intervene(Context ctx, String reason) {
        Intent i = new Intent(ctx, BlockScreenActivity.class);
        i.putExtra("reason", reason);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }
}
