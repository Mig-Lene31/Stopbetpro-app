package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("PAINEL STOPBET PRO\n\nFASE 2.1 OK");
        tv.setTextSize(22);
        tv.setPadding(40, 40, 40, 40);

        setContentView(tv);
    }
}
