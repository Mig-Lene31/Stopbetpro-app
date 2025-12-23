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
        layout.setPadding(40,40,40,40);

        TextView status = new TextView(this);
        status.setText("Motor desligado");

        Button ativar = new Button(this);
        ativar.setText("Ativar motor");

        Button simular = new Button(this);
        simular.setText("Simular saldo 150");

        ativar.setOnClickListener(v -> {
            AppState.setEngineEnabled(this, true);
            status.setText("Motor ATIVO");
        });

        simular.setOnClickListener(v -> {
            // saldo simulado
            float saldoAtual = 150f;
            EngineRunner.run(this, saldoAtual);

            if (AppState.isBlocked(this)) {
                status.setText("BLOQUEADO AUTOMATICAMENTE (12h)");
            } else {
                status.setText("Nenhum bloqueio");
            }
        });

        layout.addView(status);
        layout.addView(ativar);
        layout.addView(simular);

        setContentView(layout);
    }
}
