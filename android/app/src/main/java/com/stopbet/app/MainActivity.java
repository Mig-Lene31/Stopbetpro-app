package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int tapCount = 0;
    private long lastTap = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userId = UserIdentity.getId(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);
        title.setGravity(Gravity.CENTER);

        // 🔐 ACESSO ADM OCULTO (5 TOQUES)
        title.setOnClickListener(v -> {
            long now = System.currentTimeMillis();
            if (now - lastTap > 1500) tapCount = 0;
            lastTap = now;
            tapCount++;

            if (tapCount >= 5) {
                tapCount = 0;
                startActivity(new Intent(this, AdminLoginActivity.class));
            }
        });

        TextView idView = new TextView(this);
        idView.setText("ID do usuário:\n" + userId);
        idView.setGravity(Gravity.CENTER);

        Button btnLimits = new Button(this);
        btnLimits.setText("Stop Win / Loss");
        btnLimits.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class))
        );

        Button btnTime = new Button(this);
        btnTime.setText("Stop por Tempo");
        btnTime.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class))
        );

        Button btnDeposit = new Button(this);
        btnDeposit.setText("Configuração de Depósito");
        btnDeposit.setOnClickListener(v ->
                startActivity(new Intent(this, DepositActivity.class))
        );

        Button btnMotor = new Button(this);
        updateMotorText(btnMotor);
        btnMotor.setOnClickListener(v -> {
            boolean enabled = EngineToggleStore.isEnabled(this);
            EngineToggleStore.set(this, !enabled);
            updateMotorText(btnMotor);
        });

        Button btnInfo = new Button(this);
        btnInfo.setText("Informações");
        btnInfo.setOnClickListener(v ->
                startActivity(new Intent(this, InfoActivity.class))
        );

        layout.addView(title);
        layout.addView(idView);
        layout.addView(btnLimits);
        layout.addView(btnTime);
        layout.addView(btnDeposit);
        layout.addView(btnMotor);
        layout.addView(btnInfo);

        setContentView(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 🔴 BLOQUEIO AZUL APENAS SE STOP ESTIVER ATIVO
        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, LockScreenActivity.class));
            finish();
        }
    }

    private void updateMotorText(Button btn) {
        if (EngineToggleStore.isEnabled(this)) {
            btn.setText("Motor: ATIVADO");
        } else {
            btn.setText("Motor: DESATIVADO");
        }
    }
}
