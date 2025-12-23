package com.stopbet.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class DepositActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("Depósito\n\nTela estável");
        tv.setTextSize(22);
        tv.setTextColor(Color.parseColor("#1E3A5F"));
        tv.setPadding(50, 60, 50, 60);

        setContentView(tv);
    }
}
