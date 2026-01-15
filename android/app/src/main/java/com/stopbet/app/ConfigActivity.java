package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
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
        root.setPadding(60, 60, 60, 60);

        TextView title = new TextView(this);
        title.setText("Configuração da Proteção");
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);

        Button deposit = new Button(this);
        deposit.setText("VALOR DO DEPÓSITO");
        deposit.setOnClickListener(v ->
                startActivity(new Intent(this, DepositActivity.class))
        );

        Button limits = new Button(this);
        limits.setText("STOP WIN / LOSS");
        limits.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class))
        );

        Button time = new Button(this);
        time.setText("STOP TEMPO");
        time.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class))
        );

        Button activate = new Button(this);
        activate.setText("ATIVAR PROTEÇÃO");
        activate.setOnClickListener(v -> {

            String error = ConfigValidator.validate(this);

            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                return;
            }

            AppProtectionState.setConfigReady(this, true);
            AppProtectionState.setMotorEnabled(this, true);

            startService(new Intent(this, StopHeartService.class));

            Toast.makeText(
                    this,
                    "Proteção ativada com sucesso",
                    Toast.LENGTH_SHORT
            ).show();

        });

        root.addView(title);
        root.addView(deposit);
        root.addView(limits);
        root.addView(time);
        root.addView(activate);

        setContentView(root);
    }
}
