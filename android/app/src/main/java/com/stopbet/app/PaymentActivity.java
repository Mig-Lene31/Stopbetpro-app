package com.stopbet.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PaymentActivity extends SafeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        safeSetContentView(R.layout.activity_payment);

        Button btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class))
        );
    }
}
