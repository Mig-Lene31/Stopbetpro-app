package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Button btn = findViewById(R.id.btnPaid);
        btn.setOnClickListener(v -> {
            AppStorage.setPaid(this, true);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
