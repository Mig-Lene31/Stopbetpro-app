package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView status;
    private TextView summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.status);
        summary = findViewById(R.id.summary);

        Button btnDeposit = findViewById(R.id.btnDeposit);
        Button btnTime = findViewById(R.id.btnTime);
        Button btnLimits = findViewById(R.id.btnLimits);
        Button btnUnlock = findViewById(R.id.btnUnlock12h);
        Button btnRules = findViewById(R.id.btnRules);

        btnDeposit.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, DepositActivity.class)));

        btnTime.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TimeActivity.class)));

        btnLimits.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LimitsActivity.class)));

        btnUnlock.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Unlock12hActivity.class)));

        btnRules.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RulesActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean ativo = AppStorage.isActive(this);

        if (ativo) {
            status.setText("Status: ATIVO");
            status.setTextColor(0xFF4CAF50);
        } else {
            status.setText("Status: BLOQUEADO");
            status.setTextColor(0xFFFF5252);
        }

        summary.setText(
                "Depósito: R$ " + AppStorage.getDeposit(this) + "\n" +
                "Tempo: " + AppStorage.getTime(this) + " min/dia\n" +
                "Stop Win: R$ " + AppStorage.getWin(this) + "\n" +
                "Stop Loss: R$ " + AppStorage.getLoss(this)
        );
    }
}
