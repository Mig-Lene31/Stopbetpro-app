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

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);
        title.setGravity(Gravity.CENTER);

        // 🔐 ADM oculto
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

        Button btnLimits = new Button(this);
        btnLimits.setText("Configurar Stop Win / Loss");
        btnLimits.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class))
        );

        Button btnTime = new Button(this);
        btnTime.setText("Configurar Tempo Diário");
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
        layout.addView(btnLimits);
        layout.addView(btnTime);
        layout.addView(btnMotor);

        setContentView(layout);
    }

    private void updateMotorText(Button btn) {
        if (EngineToggleStore.isEnabled(this)) {
            btn.setText("Motor: ATIVADO");
        } else {
            btn.setText("Motor: DESATIVADO");
        }
    }
}
