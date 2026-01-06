package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(40,40,40,40);

        ScrollView scroll = new ScrollView(this);
        TextView text = new TextView(this);
        text.setText(
            "AVISO IMPORTANTE – LEIA COM ATENÇÃO\n\n" +
            "Este aplicativo NÃO é uma casa de apostas e NÃO garante ganhos financeiros.\n\n" +
            "O StopBet Pro é uma ferramenta de controle e proteção.\n\n" +
            "Bloqueios podem ocorrer automaticamente sem aviso prévio.\n\n" +
            "O aplicativo não diferencia dinheiro real de bônus.\n\n" +
            "O usuário é totalmente responsável pelos valores informados.\n\n" +
            "Ao aceitar, você concorda com todas essas condições."
        );

        scroll.addView(text);

        Button accept = new Button(this);
        accept.setText("Li e aceito");
        accept.setOnClickListener(v -> {
            InfoAcceptedStore.accept(this);
            startActivity(new Intent(this, GateActivity.class));
            finish();
        });

        root.addView(scroll);
        root.addView(accept);

        setContentView(root);
    }
}
