package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity extends Activity {

    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60,60,60,60);

        EditText deposit = new EditText(this);
        deposit.setHint("Depósito inicial");

        EditText stopLoss = new EditText(this);
        stopLoss.setHint("Stop Loss");

        EditText stopWin = new EditText(this);
        stopWin.setHint("Stop Win");

        status = new TextView(this);
        updateStatus();

        Button toggle = new Button(this);
        toggle.setText("ALTERNAR MOTOR");

        toggle.setOnClickListener(v -> {
            try {
                double d = parse(deposit.getText().toString());
                double sl = parse(stopLoss.getText().toString());
                double sw = parse(stopWin.getText().toString());

                if (d <= 0) {
                    Toast.makeText(this, "Depósito inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                DepositStore.set(this, d);
                LimitsStore.setLoss(this, (float) sl);
                LimitsStore.setWin(this, (float) sw);

                if (MotorStateStore.isRunning(this)) {
                    stopService(new android.content.Intent(this, StopHeartService.class));
                } else {
                    startService(new android.content.Intent(this, StopHeartService.class));
                }

                status.postDelayed(this::updateStatus, 300);

            } catch (Exception e) {
                Toast.makeText(this, "Use apenas números", Toast.LENGTH_SHORT).show();
            }
        });

        root.addView(deposit);
        root.addView(stopLoss);
        root.addView(stopWin);
        root.addView(status);
        root.addView(toggle);

        setContentView(root);
    }

    private void updateStatus() {
        status.setText(
                MotorStateStore.isRunning(this)
                        ? "Motor ATIVO"
                        : "Motor DESATIVADO"
        );
    }

    private double parse(String v) {
        if (v == null || v.trim().isEmpty()) return 0;
        return Double.parseDouble(v.replace(",", "."));
    }
}
