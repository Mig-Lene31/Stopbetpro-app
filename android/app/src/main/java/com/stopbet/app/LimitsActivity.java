package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LimitsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limits);

        EditText win = findViewById(R.id.inputWin);
        EditText loss = findViewById(R.id.inputLoss);
        Button btnSave = findViewById(R.id.btnSaveLimits);

        btnSave.setOnClickListener(v -> {
            float w = Float.parseFloat(win.getText().toString());
            float l = Float.parseFloat(loss.getText().toString());
            AppStorage.saveLimits(this, w, l);
            finish();
        });
    }
}
