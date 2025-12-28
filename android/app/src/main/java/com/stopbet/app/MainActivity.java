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

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);

        TextView userId = new TextView(this);
        userId.setText("ID do usuário: " + UserIdentity.getId(this));

        TextView unlockCode = new TextView(this);
        unlockCode.setText(
                "CÓDIGO: " +
                UnlockCodeGenerator.generate(UserIdentity.getId(this))
        );

        status = new TextView(this);

        Button motor = new Button(this);
        motor.setText("Ativar / Desativar motor");
        motor.setOnClickListener(v -> {
            if (!AppStateAdmin.isReleased(this) || EngineState.isBlocked(this)) {
                atualizarStatus();
                return;
            }
            MotorState.setEnabled(this, !MotorState.isEnabled(this));
            atualizarStatus();
        });

        Button simWin = new Button(this);
        simWin.setText("Simular +10 (WIN)");
        simWin.setOnClickListener(v -> {
            if (!EngineGuard.canUseMotor(this)) return;
            saldoSimulado += 10f;
            EngineExecutor.process(this, saldoSimulado);
            atualizarStatus();
        });

        Button simLoss = new Button(this);
        simLoss.setText("Simular -10 (LOSS)");
        simLoss.setOnClickListener(v -> {
            if (!EngineGuard.canUseMotor(this)) return;
            saldoSimulado -= 10f;
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

        Button unlock = new Button(this);
        unlock.setText("🔓 Desbloquear acesso");
        unlock.setOnClickListener(v ->
                startActivity(new Intent(this, UnlockActivity.class))
        );

        Button info = new Button(this);
        info.setText("📘 Informações e uso");
        info.setOnClickListener(v ->
                startActivity(new Intent(this, InfoActivity.class))
        );

        layout.addView(title);
        layout.addView(userId);
        layout.addView(unlockCode);
        layout.addView(status);
        layout.addView(motor);
        layout.addView(simWin);
        layout.addView(simLoss);
        layout.addView(limites);
        layout.addView(tempo);
        layout.addView(unlock);
        layout.addView(info);

        setContentView(layout);
        atualizarStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
        }
    }

    private void atualizarStatus() {
        if (EngineState.isBlocked(this)) {
            status.setText("⛔ Acesso bloqueado");
            return;
        }

        if (!AppStateAdmin.isReleased(this)) {
            status.setText("🔒 Aguardando liberação do administrador");
            return;
        }

        if (MotorState.isEnabled(this)) {
            status.setText("🟢 Motor ATIVO | Saldo: " + saldoSimulado);
        } else {
            status.setText("🔴 Motor DESLIGADO | Saldo: " + saldoSimulado);
        }
    }
}
