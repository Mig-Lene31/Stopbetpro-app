package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LimitsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Stop Win / Stop Loss\n(em breve)");
        tv.setPadding(40,40,40,40);
        setContentView(tv);
    }
}
