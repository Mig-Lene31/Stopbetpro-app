package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTime = findViewById(R.id.btnTime);
        Button btnLimits = findViewById(R.id.btnLimits);
        TextView status = findViewById(R.id.status);

        if (AppStorage.isConfigured(this)) {
            status.setText("Status: CONFIGURADO");
        } else {
            status.setText("Status: DESATIVADO");
        }

        btnTime.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class)));

        btnLimits.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class)));
    }
}
