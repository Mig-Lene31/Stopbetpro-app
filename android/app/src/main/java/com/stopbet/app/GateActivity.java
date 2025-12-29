package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    private int tapCount = 0;
    private long lastTapTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
            return;
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("STOPBET PRO");
        title.setTextSize(22);

        title.setOnClickListener(v -> {
            long now = System.currentTimeMillis();

            if (now - lastTapTime > 2000) {
                tapCount = 0;
            }

            tapCount++;
            lastTapTime = now;

            if (tapCount == 5) {
                tapCount = 0;
                startActivity(new Intent(this, AdminActivity.class));
            }
        });

        TextView idView = new TextView(this);
        idView.setText("SEU ID:\n" + UserIdentity.getId(this));

        TextView status = new TextView(this);
        status.setText(
                LicenseState.isValid(this) || AdminUnlockStore.isAuthorized(this)
                        ? "LICENÇA ATIVA ✅"
                        : "LICENÇA BLOQUEADA 🔒"
        );

        Button entrar = new Button(this);
        entrar.setText("ENTRAR");
        entrar.setEnabled(
                LicenseState.isValid(this) || AdminUnlockStore.isAuthorized(this)
        );

        entrar.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        layout.addView(title);
        layout.addView(idView);
        layout.addView(status);
        layout.addView(entrar);

        setContentView(layout);
    }
}
