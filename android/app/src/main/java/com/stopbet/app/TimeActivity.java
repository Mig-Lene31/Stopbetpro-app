package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        EditText input = findViewById(R.id.inputTime);
        Button btnSave = findViewById(R.id.btnSaveTime);

        btnSave.setOnClickListener(v -> {
            int minutes = Integer.parseInt(input.getText().toString());
            AppStorage.saveTime(this, minutes);
            finish();
        });
    }
}
