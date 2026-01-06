package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    private int tapCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userId = UserIdentity.getId(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        layout.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setGravity(Gravity.CENTER);
        info.setText(
            "ACESSO BLOQUEADO\n\n" +
            "Para liberar o app por 30 dias:\n\n" +
            "PIX: SEU_PIX_AQUI\n" +
            "WhatsApp: SEU_WHATSAPP_AQUI\n\n" +
            "Valor antecipado (12h): R$ 50,00\n\n" +
            "SEU ID:\n" + userId + "\n\n" +
            "Envie o comprovante junto com seu ID."
        );

        info.setOnClickListener(v -> {
            tapCount++;
            if (tapCount >= 5) {
                startActivity(new Intent(this, AdminLoginActivity.class));
                tapCount = 0;
            }
        });

        layout.addView(info);
        setContentView(layout);
    }
}
