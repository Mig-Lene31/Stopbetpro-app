package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
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
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView info = new TextView(this);
        info.setText(
            "STOPBET PRO\n\n" +
            "Este aplicativo tem como finalidade ajudar a FREAR o impulso em jogos de apostas online.\n\n" +
            "RISCO E AVISO:\n" +
            "- Não garante ganhos financeiros.\n" +
            "- Pode haver margem de erro no bloqueio.\n\n" +
            "PERMISSÕES:\n" +
            "- Acessibilidade: leitura de saldo visível.\n" +
            "- VPN local: bloqueio de sites.\n\n" +
            "FUNCIONAMENTO:\n" +
            "- Defina seu saldo de depósito.\n" +
            "- Configure Stop Win / Stop Loss / Tempo.\n" +
            "- Ative o motor quando for jogar.\n\n" +
            "Este app NÃO coleta dados pessoais nem envia informações para servidores externos."
        );

        Button ok = new Button(this);
        ok.setText("Li e aceito");
        ok.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        layout.addView(info);
        layout.addView(ok);
        scroll.addView(layout);
        setContentView(scroll);
    }
}
