package com.stopbet.app;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userId = UserIdentity.getId(this);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(50, 50, 50, 50);
        root.setBackgroundColor(0xFF0D1B2A);

        TextView title = new TextView(this);
        title.setText("Kairós — Liberação de Acesso");
        title.setTextSize(26);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);

        TextView status = new TextView(this);
        status.setText("\nSTATUS: AGUARDANDO LIBERAÇÃO\n");
        status.setTextSize(18);
        status.setTextColor(0xFFFFC107);
        status.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText(
            "Para liberar o acesso:\n\n" +
            "1) Faça o pagamento\n" +
            "2) Envie o comprovante no WhatsApp\n" +
            "3) Envie o PRINT desta tela com o ID abaixo\n"
        );
        info.setTextSize(16);
        info.setTextColor(0xFFB0BEC5);
        info.setGravity(Gravity.CENTER);

        TextView pix = new TextView(this);
        pix.setText("\nPIX: 11970200771");
        pix.setTextSize(20);
        pix.setTextColor(0xFF2E7D32);
        pix.setGravity(Gravity.CENTER);

        TextView zap = new TextView(this);
        zap.setText("\nWhatsApp: (11) 97020-0771");
        zap.setTextSize(16);
        zap.setTextColor(0xFFFFFFFF);
        zap.setGravity(Gravity.CENTER);

        TextView id = new TextView(this);
        id.setText("\nSEU ID ÚNICO:\n" + userId);
        id.setTextSize(18);
        id.setTextColor(0xFFFFFFFF);
        id.setGravity(Gravity.CENTER);

        TextView lock = new TextView(this);
        lock.setText("\n⛔ O APP SÓ SERÁ LIBERADO APÓS CONFIRMAÇÃO MANUAL ⛔");
        lock.setTextSize(14);
        lock.setTextColor(0xFFEF5350);
        lock.setGravity(Gravity.CENTER);

        root.addView(title);
        root.addView(status);
        root.addView(info);
        root.addView(pix);
        root.addView(zap);
        root.addView(id);
        root.addView(lock);

        setContentView(root);
    }
}
