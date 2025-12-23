package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("PAINEL STOPBET PRO\nFASE 2 FINAL\nVERSÃO 2.0.3");
        tv.setTextSize(20);
        tv.setPadding(40,40,40,40);

        setContentView(tv);
    }
}
