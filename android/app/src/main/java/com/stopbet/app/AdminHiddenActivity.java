package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdminHiddenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);
        root.setBackgroundColor(0xFF0D1B2A);

        TextView title = new TextView(this);
        title.setText("ADM — Controle Interno");
        title.setTextColor(0xFFFFFFFF);
        title.setTextSize(22);
        title.setGravity(Gravity.CENTER);

        TextView status = new TextView(this);
        boolean enabled = MotorStateStore.isEnabled(this);
        status.setText(enabled ? "🟢 Motor ATIVO" : "🔴 Motor DESATIVADO");
        status.setTextColor(0xFFDDDDDD);
        status.setGravity(Gravity.CENTER);

        Button toggle = new Button(this);
        toggle.setText(enabled ? "DESATIVAR MOTOR" : "ATIVAR MOTOR");

        toggle.setOnClickListener(v -> {
            MotorStateStore.setEnabled(this, !MotorStateStore.isEnabled(this));
            recreate();
        });

        root.addView(title);
        root.addView(status);
        root.addView(toggle);

        setContentView(root);
    }
}
