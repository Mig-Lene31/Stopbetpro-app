package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlockedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView msg = new TextView(this);
        msg.setText("⛔ Acesso bloqueado.\n\nLimite atingido ou bloqueio ativo.");
        msg.setTextSize(18);

        layout.addView(msg);
        setContentView(layout);
    }
}
