package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60,60,60,60);

        TextView status = new TextView(this);
        status.setText(
            EngineRuntime.isRunning(this)
                ? "Motor ATIVO"
                : "Motor DESATIVADO"
        );

        Button toggle = new Button(this);
        toggle.setText("ALTERNAR MOTOR");

        toggle.setOnClickListener(v -> {
            if (EngineRuntime.isRunning(this)) {
                EngineRuntime.stop(this);
                status.setText("Motor DESATIVADO");
            } else {
                EngineRuntime.start(this);
                status.setText("Motor ATIVO");
            }
            Toast.makeText(this, "Estado atualizado", Toast.LENGTH_SHORT).show();
        });

        root.addView(status);
        root.addView(toggle);
        setContentView(root);
    }
}
