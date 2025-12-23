package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("Motor do StopBet");
        title.setTextSize(20);

        TextView status = new TextView(this);
        status.setText("Nenhum bloqueio");

        Button ativar = new Button(this);
        ativar.setText("Ativar motor");

        Button simular = new Button(this);
        simular.setText("Simular saldo 150");

        Button voltar = new Button(this);
        voltar.setText("⬅ Voltar");
        voltar.setOnClickListener(v -> finish());

        layout.addView(title);
        layout.addView(status);
        layout.addView(ativar);
        layout.addView(simular);
        layout.addView(voltar);

        setContentView(layout);
    }
}
