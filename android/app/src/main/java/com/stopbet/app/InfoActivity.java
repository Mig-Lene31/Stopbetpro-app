package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("📘 Informações do StopBet Pro");
        title.setTextSize(22);

        TextView info = new TextView(this);
        info.setText(
                "OBJETIVO DO APP\n\n" +
                "Este aplicativo foi criado para ajudar no controle do uso de sites de apostas.\n\n" +

                "FUNCIONALIDADES\n\n" +
                "• Stop Win e Stop Loss\n" +
                "• Bloqueio automático por tempo\n" +
                "• Bloqueio de 12 horas ao atingir limites\n" +
                "• Tela de bloqueio com cronômetro\n\n" +

                "DESBLOQUEIO\n\n" +
                "• O desbloqueio antes das 12h exige pagamento\n" +
                "• PIX informado na tela de bloqueio\n\n" +

                "AVISO\n\n" +
                "Este app não incentiva apostas.\n" +
                "Ele existe para LIMITAR e CONTROLAR."
        );
        info.setTextSize(16);

        layout.addView(title);
        layout.addView(info);

        scroll.addView(layout);
        setContentView(scroll);
    }
}
