package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Unlock12hActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_12h);

        Button btn = findViewById(R.id.btnUnlock);
        btn.setOnClickListener(v -> {
            AppStorage.activateTemporary(this);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
