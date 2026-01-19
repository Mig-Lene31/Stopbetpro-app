package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stopbet.app.runtime.BalanceSimulator;

public class SimulateActivity extends Activity {

    TextView balanceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60,60,60,60);

        balanceView = new TextView(this);
        update();

        Button add = new Button(this);
        add.setText("+10");
        add.setOnClickListener(v -> {
            BalanceSimulator.simulate(this, 10);
            update();
        });

        Button sub = new Button(this);
        sub.setText("-10");
        sub.setOnClickListener(v -> {
            BalanceSimulator.simulate(this, -10);
            update();
        });

        root.addView(balanceView);
        root.addView(add);
        root.addView(sub);

        setContentView(root);
    }

    private void update() {
        balanceView.setText("Saldo: " + AppState.getBalance(this));
    }
}
