package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConfigActivity extends Activity {

    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60,60,60,60);

        status = new TextView(this);
        updateStatus();

        Button toggle = new Button(this);
        toggle.setText("ALTERNAR MOTOR");

        toggle.setOnClickListener(v -> {
            if (MotorStateStore.isRunning(this)) {
                stopService(new Intent(this, StopHeartService.class));
            } else {
                Intent i = new Intent(this, StopHeartService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(i);
                } else {
                    startService(i);
                }
            }
            status.postDelayed(this::updateStatus, 500);
        });

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
}
