package com.stopbet.app;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TextView;

public class MotorStatusBanner {

    public static TextView create(Context ctx) {
        TextView tv = new TextView(ctx);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(16);
        tv.setTypeface(null, Typeface.BOLD);

        if (MotorStateStore.isEnabled(ctx)) {
            tv.setText("🟢 PROTEÇÃO ATIVA");
            tv.setTextColor(0xFF2E7D32);
        } else {
            tv.setText("⚪ PROTEÇÃO DESATIVADA");
            tv.setTextColor(0xFFBBBBBB);
        }

        return tv;
    }
}
