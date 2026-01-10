package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConfigActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);
        root.setBackgroundColor(0xFF0D1B2A);

        TextView title = new TextView(this);
        title.setText("Configurações");
        title.setTextSize(22);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);

        Button toggle = new Button(this);

        boolean enabled = MotorStateStore.isEnabled(this);
        toggle.setText(enabled ? "DESATIVAR PROTEÇÃO" : "ATIVAR PROTEÇÃO");

        toggle.setOnClickListener(v -> {
            boolean current = MotorStateStore.isEnabled(this);
            MotorStateStore.setEnabled(this, !current);
            toggle.setText(!current ? "DESATIVAR PROTEÇÃO" : "ATIVAR PROTEÇÃO");
        });

        root.addView(title);
        root.addView(toggle);

        setContentView(root);
    }
}
