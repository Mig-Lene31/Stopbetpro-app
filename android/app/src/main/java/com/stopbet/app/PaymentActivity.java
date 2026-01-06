package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
            "Envie o comprovante junto com seu ID."
        );

        Button adm = new Button(this);
        adm.setText("Entrar como ADM");
        adm.setOnClickListener(v ->
            startActivity(new Intent(this, AdminLoginActivity.class))
        );

        layout.addView(info);
        layout.addView(adm);

        setContentView(layout);
    }
}
