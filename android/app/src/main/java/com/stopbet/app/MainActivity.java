package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDeposit = findViewById(R.id.btnDeposit);
        Button btnLimits = findViewById(R.id.btnLimits);
        Button btnTime = findViewById(R.id.btnTime);
        Button btnUnlock = findViewById(R.id.btnUnlock12h);
        Button btnRules = findViewById(R.id.btnRules);

        btnDeposit.setOnClickListener(v ->
                startActivity(new Intent(this, DepositActivity.class)));

        btnLimits.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class)));

        btnTime.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class)));

        btnUnlock.setOnClickListener(v ->
                startActivity(new Intent(this, Unlock12hActivity.class)));

        btnRules.setOnClickListener(v ->
                startActivity(new Intent(this, RulesActivity.class)));
    }
}
