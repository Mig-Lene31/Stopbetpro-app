package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
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
        root.setBackgroundColor(UiStyle.background());

        TextView title = new TextView(this);
        title.setText("Configurações");
        UiStyle.applyTitle(title);
        title.setGravity(Gravity.CENTER);

        TextView status = MotorStatusBanner.create(this);

        Button deposit = new Button(this);
        deposit.setText("VALOR DO DEPÓSITO");
        deposit.setOnClickListener(v ->
                startActivity(new Intent(this, DepositActivity.class))
        );

        Button limits = new Button(this);
        limits.setText("LIMITES DE GANHO / PERDA");
        limits.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class))
        );

        Button time = new Button(this);
        time.setText("TEMPO DE JOGO");
        time.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class))
        );

        Button toggle = new Button(this);
        boolean enabled = MotorStateStore.isEnabled(this);
        toggle.setText(enabled ? "DESATIVAR PROTEÇÃO" : "ATIVAR PROTEÇÃO");

        toggle.setOnClickListener(v -> {
            boolean current = MotorStateStore.isEnabled(this);
            MotorStateStore.setEnabled(this, !current);
            recreate();
        });

        Button back = new Button(this);
        back.setText("VOLTAR");
        back.setOnClickListener(v -> finish());

        root.addView(title);
        root.addView(status);
        root.addView(deposit);
        root.addView(limits);
        root.addView(time);
        root.addView(toggle);
        root.addView(back);

        setContentView(root);
    }
}
