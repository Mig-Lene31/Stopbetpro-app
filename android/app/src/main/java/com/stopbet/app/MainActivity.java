package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView status;
    private float saldoSimulado = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        final int[] taps = {0};
        final long[] lastTap = {0};

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);

        title.setOnClickListener(v -> {
            long now = System.currentTimeMillis();

            if (now - lastTap[0] > 3000) {
                taps[0] = 0;
            }

            lastTap[0] = now;
            taps[0]++;

            if (taps[0] == 5) {
                taps[0] = 0;
                startActivity(new Intent(this, AdminActivity.class));
            }
        });
        final int[] taps = {0};
        final long[] lastTap = {0};

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);

        title.setOnClickListener(v -> {
            long now = System.currentTimeMillis();

            if (now - lastTap[0] > 3000) {
                taps[0] = 0;
            }

            lastTap[0] = now;
            taps[0]++;

            if (taps[0] == 5) {
                taps[0] = 0;
                startActivity(new Intent(this, AdminActivity.class));
            }
        });
        final int[] taps = {0};
        final long[] lastTap = {0};

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);

        title.setOnClickListener(v -> {
            long now = System.currentTimeMillis();

            if (now - lastTap[0] > 3000) {
                taps[0] = 0;
            }

            lastTap[0] = now;
            taps[0]++;

            if (taps[0] == 5) {
                taps[0] = 0;
                startActivity(new Intent(this, AdminActivity.class));
            }
        });

        status = new TextView(this);

        Button motor = new Button(this);
        motor.setText("Ativar / Desativar motor");
        motor.setOnClickListener(v -> {

            if (!AppStateAdmin.isReleased(this)
                    || !LicenseState.isValid(this)
                    || EngineState.isBlocked(this)
                    || DailyTimeEngine.exceeded(this)) {

                atualizarStatus();
                return;
            }

            boolean atual = MotorState.isEnabled(this);
            MotorState.setEnabled(this, !atual);
            atualizarStatus();
        });

        Button simular = new Button(this);
        simular.setText("Simular saldo +10");
        simular.setOnClickListener(v -> {

            if (!EngineGuard.canUseMotor(this)) {
                atualizarStatus();
                return;
            }

            saldoSimulado += 10f;
            EngineExecutor.process(this, saldoSimulado);
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

        layout.addView(title);
        layout.addView(status);
        layout.addView(motor);
        layout.addView(simular);
        layout.addView(limites);
        layout.addView(tempo);

        setContentView(layout);
        atualizarStatus();
    }

    private void atualizarStatus() {

        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
            return;
        }

        if (!AppStateAdmin.isReleased(this)) {
            status.setText("🔒 Aguardando liberação do administrador");
            return;
        }

        if (!LicenseState.isValid(this)) {
            status.setText("⛔ Licença expirada");
            return;
        }

        if (DailyTimeEngine.exceeded(this)) {
            status.setText("⏱️ Limite diário atingido");
            return;
        }

        if (MotorState.isEnabled(this)) {
            status.setText("🟢 Motor ATIVO | Saldo: " + saldoSimulado);
        } else {
            status.setText("🔴 Motor DESLIGADO | Saldo: " + saldoSimulado);
        }
    }
}
