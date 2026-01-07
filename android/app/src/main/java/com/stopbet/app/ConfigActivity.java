package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfigActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button btnDeposit = findViewById(R.id.btnDeposit);
        Button btnLimits = findViewById(R.id.btnLimits);
        Button btnTime = findViewById(R.id.btnTime);
        Button btnRules = findViewById(R.id.btnRules);

        btnDeposit.setOnClickListener(v ->
                startActivity(new Intent(this, DepositActivity.class)));

        btnLimits.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class)));

        btnTime.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class)));

        btnRules.setOnClickListener(v ->
                startActivity(new Intent(this, RulesActivity.class)));
    }
}
