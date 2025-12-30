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
    private TextView balanceView;

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

        // 🔐 ADM oculto (5 toques)
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

        balanceView = new TextView(this);
        updateBalanceText();

        Button add10 = new Button(this);
        add10.setText("+ R$10 (teste)");
        add10.setOnClickListener(v -> {
            AppState.addBalance(this, 10f);
            updateBalanceText();
        });

        Button sub10 = new Button(this);
        sub10.setText("- R$10 (teste)");
        sub10.setOnClickListener(v -> {
            AppState.addBalance(this, -10f);
            updateBalanceText();
        });

        Button reset = new Button(this);
        reset.setText("Resetar saldo");
        reset.setOnClickListener(v -> {
            AppState.resetBalance(this);
            updateBalanceText();
        });

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

        Button btnMotor = new Button(this);
        updateMotorText(btnMotor);
        btnMotor.setOnClickListener(v -> {
            boolean enabled = EngineToggleStore.isEnabled(this);
            EngineToggleStore.set(this, !enabled);
            updateMotorText(btnMotor);
        });

        layout.addView(title);
        layout.addView(idView);
        layout.addView(balanceView);
        layout.addView(add10);
        layout.addView(sub10);
        layout.addView(reset);
        layout.addView(btnLimits);
        layout.addView(btnTime);
        layout.addView(btnMotor);

        setContentView(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, LockScreenActivity.class));
            finish();
            return;
        }

        startService(new Intent(this, StopHeartService.class));
        updateBalanceText();
    }

    private void updateBalanceText() {
        balanceView.setText("Saldo atual: R$ " + AppState.getCurrentBalance(this));
        balanceView.setGravity(Gravity.CENTER);
    }

    private void updateMotorText(Button btn) {
        if (EngineToggleStore.isEnabled(this)) {
            btn.setText("Motor: ATIVADO");
        } else {
            btn.setText("Motor: DESATIVADO");
        }
    }
}
