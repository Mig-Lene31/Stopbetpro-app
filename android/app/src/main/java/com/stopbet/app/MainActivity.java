package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

// 🔧 IMPORTS INTERNOS DO APP (OBRIGATÓRIOS)
import com.stopbet.app.EngineToggleStore;
import com.stopbet.app.DepositStore;
import com.stopbet.app.AdminSession;
import com.stopbet.app.UserIdentity;
import com.stopbet.app.LockScreenActivity;
import com.stopbet.app.AdminLoginActivity;
import com.stopbet.app.LimitsActivity;
import com.stopbet.app.TimeActivity;
import com.stopbet.app.DepositActivity;
import com.stopbet.app.InfoActivity;

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

        // ===== ACESSO ADM SECRETO (5 TOQUES) =====
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
        btnDeposit.setText("Depósito");
        btnDeposit.setOnClickListener(v ->
                startActivity(new Intent(this, DepositActivity.class))
        );

        // ===== MOTOR ON / OFF (USUÁRIO) =====
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

    // 🔒 TRAVA REAL DO APP
    @Override
    protected void onResume() {
        super.onResume();

        // 1️⃣ ADM não liberou
        if (!AdminSession.isUnlocked(this)) {
            startActivity(new Intent(this, LockScreenActivity.class));
            finish();
            return;
        }

        // 2️⃣ Depósito não informado
        String deposit = DepositStore.getValue(this);
        if (deposit == null || deposit.trim().isEmpty() || deposit.equals("0")) {
            startActivity(new Intent(this, DepositActivity.class));
            return;
        }

        // 3️⃣ Motor desligado
        if (!EngineToggleStore.isEnabled(this)) {
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
