package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DepositActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("Depósito\n\nTela estável");
        tv.setTextSize(20);
        tv.setPadding(40,40,40,40);

        setContentView(tv);
    }
}
