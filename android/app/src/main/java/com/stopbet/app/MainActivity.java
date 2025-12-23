package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("Painel StopBet Pro");
        title.setTextSize(20);

        Button dep = new Button(this);
        dep.setText("Depósito");
        dep.setOnClickListener(v ->
                startActivity(new Intent(this, DepositActivity.class)));

        Button limits = new Button(this);
        limits.setText("Stop Win / Stop Loss");
        limits.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class)));

        Button time = new Button(this);
        time.setText("Tempo de Uso");
        time.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class)));

        Button rules = new Button(this);
        rules.setText("Regras");
        rules.setOnClickListener(v ->
                startActivity(new Intent(this, RulesActivity.class)));

        layout.addView(title);
        layout.addView(dep);
        layout.addView(limits);
        layout.addView(time);
        layout.addView(rules);

        setContentView(layout);
    }
}
