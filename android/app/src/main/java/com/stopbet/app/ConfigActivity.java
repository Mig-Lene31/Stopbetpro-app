package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConfigActivity extends Activity {

    TextView status;

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
            if (EngineRuntime.isRunning(this)) {
                EngineRuntime.requestStop(this);
            } else {
                EngineRuntime.requestStart(this);
            }
            status.postDelayed(this::updateStatus, 500);
        });

        root.addView(status);
        root.addView(toggle);
        setContentView(root);
    }

    private void updateStatus() {
        status.setText(
            EngineRuntime.isRunning(this)
                ? "Motor ATIVO"
                : "Motor DESATIVADO"
        );
    }
}
