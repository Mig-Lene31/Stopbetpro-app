package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private float saldo = 0f;
    private TextView status;
    private TextView saldoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
            return;
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);

        status = new TextView(this);
        saldoView = new TextView(this);

        Button motor = new Button(this);
        motor.setText("Ativar / Desativar Motor");
        motor.setOnClickListener(v -> {
            if (EngineState.isBlocked(this)) return;
            boolean novoEstado = !MotorState.isEnabled(this);
            MotorState.setEnabled(this, novoEstado);
            if (novoEstado) {
                RealTimeEngine.start(this);
            } else {
                RealTimeEngine.stop(this);
            }
            atualizar();
            if (RealTimeWatcher.check(this)) {
                startActivity(new Intent(this, BlockedActivity.class));
                finish();
            }
            atualizar();
            if (RealTimeWatcher.check(this)) {
                startActivity(new Intent(this, BlockedActivity.class));
                finish();
            }
        });

        Button simular = new Button(this);
        simular.setText("Simular +10");
        simular.setOnClickListener(v -> {
            if (!MotorState.isEnabled(this)) return;

            saldo += 10f;

            boolean bloqueouValor = EngineExecutor.process(this, saldo);
            TimeEngine.tick(this);

            atualizar();
            if (RealTimeWatcher.check(this)) {
                startActivity(new Intent(this, BlockedActivity.class));
                finish();
            }

            if (bloqueouValor || EngineState.isBlocked(this)) {
                startActivity(new Intent(this, BlockedActivity.class));
                finish();
            }
        });

        Button limites = new Button(this);
        limites.setText("Configurar Stop Win / Loss");
        limites.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class))
        );

        Button tempo = new Button(this);
        tempo.setText("Configurar Stop Tempo");
        tempo.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class))
        );

        Button info = new Button(this);
        info.setText("Informações do App");
        info.setOnClickListener(v ->
                startActivity(new Intent(this, InfoActivity.class))
        );

        layout.addView(title);
        layout.addView(status);
        layout.addView(saldoView);
        layout.addView(motor);
        layout.addView(simular);
        layout.addView(limites);
        layout.addView(tempo);
        layout.addView(info);

        setContentView(layout);
        atualizar();
            if (RealTimeWatcher.check(this)) {
                startActivity(new Intent(this, BlockedActivity.class));
                finish();
            }
    }

    private void atualizar() {
        status.setText(
                EngineState.isBlocked(this) ? "🔴 BLOQUEADO" :
                MotorState.isEnabled(this) ? "🟢 Motor ATIVO" :
                "⚪ Motor DESLIGADO"
        );
        saldoView.setText("Saldo: " + saldo);
    }
}
