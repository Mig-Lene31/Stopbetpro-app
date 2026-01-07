package com.stopbet.app;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentInfoView {

    public static LinearLayout build(Context ctx) {

        LinearLayout box = new LinearLayout(ctx);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(40, 40, 40, 40);
        box.setBackgroundColor(0xFF102A43);
        box.setGravity(Gravity.CENTER);

        TextView title = new TextView(ctx);
        title.setText("Acesso ao Kairós");
        title.setTextSize(20);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);

        TextView pix = new TextView(ctx);
        pix.setText("\nPIX: 11970200771");
        pix.setTextSize(18);
        pix.setTextColor(0xFF2E7D32);
        pix.setGravity(Gravity.CENTER);

        TextView zap = new TextView(ctx);
        zap.setText("\nWhatsApp: (11) 97020-0771");
        zap.setTextSize(16);
        zap.setTextColor(0xFFB0BEC5);
        zap.setGravity(Gravity.CENTER);

        TextView info = new TextView(ctx);
        info.setText("\nApós o pagamento, envie o comprovante pelo WhatsApp para liberação.");
        info.setTextSize(14);
        info.setTextColor(0xFFB0BEC5);
        info.setGravity(Gravity.CENTER);

        box.addView(title);
        box.addView(pix);
        box.addView(zap);
        box.addView(info);

        return box;
    }
}
