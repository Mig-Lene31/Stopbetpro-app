package com.stopbet.app;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
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
        title.setText("Liberação do Kairós");
        title.setTextSize(26);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText(
            "\nPara liberar o uso do Kairós:\n" +
            "1) Faça o pagamento\n" +
            "2) Envie o comprovante\n" +
            "3) Envie o PRINT desta tela com o ID\n"
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
        id.setText("\nSEU ID:\n" + userId);
        id.setTextSize(18);
        id.setTextColor(0xFFFFFFFF);
        id.setGravity(Gravity.CENTER);

        Button btn = new Button(this);
        btn.setText("ENTENDI, VOU ENVIAR O PRINT");
        btn.setEnabled(false);

        root.addView(title);
        root.addView(info);
        root.addView(pix);
        root.addView(zap);
        root.addView(id);
        root.addView(btn);

        setContentView(root);
    }
}
