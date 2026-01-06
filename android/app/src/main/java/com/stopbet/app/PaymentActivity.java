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
        info.setText(
            "ACESSO BLOQUEADO\n\n" +
            "Para liberar o app:\n\n" +
            "PIX: 000.000.000-00\n" +
            "WhatsApp: (xx) xxxxx-xxxx\n\n" +
            "Envie o comprovante com seu ID."
        );
        info.setGravity(Gravity.CENTER);

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
