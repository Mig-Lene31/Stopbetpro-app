package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    private int taps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Se já estiver ativo, pula direto pra Home
        if (AppStorage.isActive(this)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_payment);

        ImageView logo = findViewById(R.id.logo);
        TextView txtId = findViewById(R.id.txtId);

        txtId.setText("ID: " + AppStorage.getUserId(this));

        logo.setOnClickListener(v -> {
            taps++;
            if (taps >= 5) {
                startActivity(new Intent(this, AdminActivity.class));
                taps = 0;
            }
        });
    }
}
