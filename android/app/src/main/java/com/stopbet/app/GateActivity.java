package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(24);

        TextView status = new TextView(this);
        status.setTextSize(16);

        if (MotorState.isEnabled(this)) {
            status.setText("🟢 Motor de proteção ATIVO");
        } else {
            status.setText("⚪ Motor de proteção DESATIVADO");
        }

        layout.addView(title);
        layout.addView(status);

        setContentView(layout);

        // segue fluxo normal
        startActivity(new Intent(this, BootActivity.class));
        finish();
    }
}
