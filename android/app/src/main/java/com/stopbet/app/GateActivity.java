package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!AppProtectionState.canAppRun(this)) {
            Toast.makeText(this,
                    "Erro crítico detectado. Reconfigure o app.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);

        TextView title = new TextView(this);
        title.setText("Kairós");
        title.setTextSize(24);
        title.setGravity(Gravity.CENTER);

        Button config = new Button(this);
        config.setText("CONFIGURAR PROTEÇÃO");
        config.setOnClickListener(v ->
                startActivity(new Intent(this, ConfigActivity.class))
        );

        Button start = new Button(this);
        start.setText("ATIVAR PROTEÇÃO");
        start.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    "Finalize a configuração antes de ativar",
                    Toast.LENGTH_SHORT
            ).show();
        });

        root.addView(title);
        root.addView(config);
        root.addView(start);
        setContentView(root);
    }
}
