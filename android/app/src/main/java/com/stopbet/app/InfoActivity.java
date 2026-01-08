package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);
        root.setBackgroundColor(0xFF0D1B2A);

        TextView title = new TextView(this);
        title.setText("Kairós — Aviso Importante");
        title.setTextSize(24);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText(
                "\nEste aplicativo ajuda no controle de apostas.\n\n" +
                "O uso é de sua responsabilidade.\n" +
                "O app pode bloquear sites automaticamente.\n\n" +
                "Ao continuar, você concorda com estes termos."
        );
        info.setTextSize(16);
        info.setTextColor(0xFFB0BEC5);
        info.setGravity(Gravity.CENTER);

        Button accept = new Button(this);
        accept.setText("ACEITAR E CONTINUAR");

        accept.setOnClickListener(v -> {
            InfoAcceptedStore.markAccepted(this);
            startActivity(new Intent(this, PaymentActivity.class));
            finish();
        });

        root.addView(title);
        root.addView(info);
        root.addView(accept);

        setContentView(root);
    }
}
