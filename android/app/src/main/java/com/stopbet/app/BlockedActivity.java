package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlockedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("⛔ Acesso temporariamente bloqueado");
        title.setTextSize(20);

        TextView msg = new TextView(this);
        msg.setText(
                "Este bloqueio foi ativado automaticamente.\n\n" +
                "Ele existe para proteger você de decisões impulsivas " +
                "que normalmente acontecem após atingir limites definidos.\n\n" +
                "⏳ O acesso será liberado automaticamente após o período de segurança.\n\n" +
                "Caso precise de liberação antecipada, utilize a opção de desbloqueio disponível no aplicativo."
        );
        msg.setTextSize(16);

        layout.addView(title);
        layout.addView(msg);

        setContentView(layout);
    }
}
