package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("STOPBET PRO\nAPP ABRIU COM SUCESSO");
        tv.setTextSize(20);
        setContentView(tv);
    }
}
