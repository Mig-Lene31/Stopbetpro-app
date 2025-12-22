package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Teste mínimo para ver se o app abre
        TextView tv = new TextView(this);
        tv.setText("StopBet Pro - teste de abertura OK");
        setContentView(tv);
    }
}
