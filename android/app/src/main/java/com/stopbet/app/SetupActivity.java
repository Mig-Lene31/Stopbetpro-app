package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SetupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60,60,60,60);

        EditText deposit = new EditText(this);
        deposit.setHint("Depósito inicial");

        EditText stopLoss = new EditText(this);
        stopLoss.setHint("Stop Loss");

        EditText stopWin = new EditText(this);
        stopWin.setHint("Stop Win");

        Button save = new Button(this);
        save.setText("CONFIRMAR PROTEÇÃO");

        save.setOnClickListener(v -> {
            double d = Double.parseDouble(deposit.getText().toString());
            double sl = Double.parseDouble(stopLoss.getText().toString());
            double sw = Double.parseDouble(stopWin.getText().toString());

            DepositStore.set(this, d);
            LimitsStore.setLoss(this, (float) sl);
            LimitsStore.setWin(this, (float) sw);

            Toast.makeText(this, "Proteção configurada", Toast.LENGTH_SHORT).show();
            finish();
        });

        root.addView(deposit);
        root.addView(stopLoss);
        root.addView(stopWin);
        root.addView(save);

        setContentView(root);
    }
}
