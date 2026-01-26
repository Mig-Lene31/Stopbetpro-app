package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(40, 80, 40, 40);

        TextView status = new TextView(this);
        status.setText("Proteção DESATIVADA");
        status.setTextSize(18f);

        Button start = new Button(this);
        start.setText("ATIVAR PROTEÇÃO");

        start.setOnClickListener(v -> {
            if (!LimitsStore.isConfigured(this)) {
                Toasts.error(this, "Finalize a configuração antes de ativar");
                return;
            }

            openAccessibility();
        });

        root.addView(status);
        root.addView(start);

        setContentView(root);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (started) return;

        if (!LimitsStore.isConfigured(this)) return;

        if (!AccessibilityUtil.isEnabled(this)) {
            openAccessibility();
            return;
        }

        started = true;

        EngineState.activate(this);

        Intent vpn = new Intent(this, KairosVpnService.class);
        startService(vpn);

        finish();
    }

    private void openAccessibility() {
        Intent i = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(i);
    }
}
