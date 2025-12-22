package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnDeposit)
                .setOnClickListener(v -> startActivity(new Intent(this, DepositActivity.class)));

        findViewById(R.id.btnLimits)
                .setOnClickListener(v -> startActivity(new Intent(this, LimitsActivity.class)));

        findViewById(R.id.btnTime)
                .setOnClickListener(v -> startActivity(new Intent(this, TimeActivity.class)));

        findViewById(R.id.btnRules)
                .setOnClickListener(v -> startActivity(new Intent(this, RulesActivity.class)));
    }
}
