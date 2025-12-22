package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView status;
    private TextView summary;
    private Button btnTime, btnLimits, btnDeposit, btnUnlock, btnRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.status);
        summary = findViewById(R.id.summary);

        btnDeposit = findViewById(R.id.btnDeposit);
        btnTime = findViewById(R.id.btnTime);
        btnLimits = findViewById(R.id.btnLimits);
        btnUnlock = findViewById(R.id.btnUnlock12h);
        btnRules = findViewById(R.id.btnRules);

        btnDeposit.setOnClickListener(v ->
                startActivity(new Intent(this, DepositActivity.class)));

        btnTime.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class)));

        btnLimits.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class)));

        btnUnlock.setOnClickListener(v ->
                startActivity(new Intent(this, Unlock12hActivity.class)));

        btnRules.setOnClickListener(v ->
                startActivity(new Intent(this, RulesActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (AppStorage.isFullyConfigured(this)) {
            status.setText("Status: ATIVO");
            status.setTextColor(0xFF4CAF50);

            summary.setText(
                    "Depósito: R$ " + AppStorage.getDeposit(this) + "\n" +
                    "Tempo: " + AppStorage.getTime(this) + " min/dia\n" +
                    "Stop Win: R$ " + AppStorage.getWin(this) + "\n" +
                    "Stop Loss: R$ " + AppStorage.getLoss(this)
            );

            btnDeposit.setEnabled(true);
            btnTime.setEnabled(true);
            btnLimits.setEnabled(true);
            btnUnlock.setEnabled(false);
            btnUnlock.setText("App Ativo");

        } else {
            status.setText("Status: BLOQUEADO");
            status.setTextColor(0xFFFF5252);

            summary.setText("Pagamento ou desbloqueio necessário");

            btnDeposit.setEnabled(false);
            btnTime.setEnabled(false);
            btnLimits.setEnabled(false);
            btnUnlock.setEnabled(true);
            btnUnlock.setText("Desbloquear por 12h (R$ 50)");
        }
    }
}
