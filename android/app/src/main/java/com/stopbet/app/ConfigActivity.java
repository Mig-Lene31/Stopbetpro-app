package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class ConfigActivity extends Activity {

    TextView status;
    EditText deposit;
    EditText stopLoss;
    EditText stopWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60,60,60,60);

        deposit = new EditText(this);
        deposit.setHint("Depósito inicial");

        stopLoss = new EditText(this);
        stopLoss.setHint("Stop Loss");

        stopWin = new EditText(this);
        stopWin.setHint("Stop Win");

        status = new TextView(this);

        Button toggle = new Button(this);
        toggle.setText("ALTERNAR MOTOR");

        toggle.setOnClickListener(v -> {
            try {
                if (!MotorStateStore.isRunning(this)) {

                    double d = parse(deposit.getText().toString());
                    double sl = parse(stopLoss.getText().toString());
                    double sw = parse(stopWin.getText().toString());

                    if (d <= 0 || sl <= 0 || sw <= 0) {
                        Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    DepositStore.set(this, d);
                    LimitsStore.setLoss(this, (float) sl);
                    LimitsStore.setWin(this, (float) sw);

                    startService(new Intent(this, StopHeartService.class));

                } else {
                    stopService(new Intent(this, StopHeartService.class));
                }

                updateUI();

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

        loadValues();
        updateUI();
    }

    private void loadValues() {
        double d = DepositStore.get(this);
        float sl = LimitsStore.getLoss(this);
        float sw = LimitsStore.getWin(this);

        if (d > 0) deposit.setText(String.valueOf(d));
        if (sl > 0) stopLoss.setText(String.valueOf(sl));
        if (sw > 0) stopWin.setText(String.valueOf(sw));
    }

    private void updateUI() {
        boolean running = MotorStateStore.isRunning(this);

        status.setText(running ? "Motor ATIVO" : "Motor DESATIVADO");

        deposit.setEnabled(!running);
        stopLoss.setEnabled(!running);
        stopWin.setEnabled(!running);
    }

    private double parse(String v) {
        if (v == null || v.trim().isEmpty()) return 0;
        return Double.parseDouble(v.replace(",", "."));
    }
}
