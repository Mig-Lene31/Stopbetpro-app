package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);

        status = new TextView(this);

        Button motor = new Button(this);
        motor.setText("Ativar / Desativar motor");
        motor.setOnClickListener(v -> {
            boolean atual = MotorState.isEnabled(this);
            MotorState.setEnabled(this, !atual);
            atualizarStatus();
        });

        Button limites = new Button(this);
        limites.setText("Configurar Win / Loss");
        limites.setOnClickListener(v ->
            startActivity(new Intent(this, LimitsActivity.class))
        );

        Button tempo = new Button(this);
        tempo.setText("Configurar Tempo");
        tempo.setOnClickListener(v ->
            startActivity(new Intent(this, TimeActivity.class))
        );

        Button voltar = new Button(this);
        voltar.setText("⬅ Voltar");
        voltar.setOnClickListener(v -> finish());

        layout.addView(title);
        layout.addView(status);
        layout.addView(motor);
        layout.addView(limites);
        layout.addView(tempo);
        layout.addView(voltar);

        setContentView(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarStatus();
    }

    private void atualizarStatus() {
        if (MotorState.isEnabled(this)) {
            status.setText("🟢 Motor ATIVO");
        } else {
            status.setText("🔴 Motor DESLIGADO");
        }
    }
}
