package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        TextView txtId = findViewById(R.id.txtId);
        Button btnEnter = findViewById(R.id.btnEnter);

        String userId = "SBP-" + System.currentTimeMillis();
        txtId.setText("ID: " + userId);

        btnEnter.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class))
        );
    }
}
