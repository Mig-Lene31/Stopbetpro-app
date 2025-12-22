package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DepositActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        EditText input = findViewById(R.id.inputDeposit);
        Button btnSave = findViewById(R.id.btnSaveDeposit);

        btnSave.setOnClickListener(v -> {
            float value = Float.parseFloat(input.getText().toString());
            AppStorage.saveDeposit(this, value);
            finish();
        });
    }
}
