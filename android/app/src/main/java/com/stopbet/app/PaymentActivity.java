package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fluxo seguro: evita crash e mantém aparência antiga
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
