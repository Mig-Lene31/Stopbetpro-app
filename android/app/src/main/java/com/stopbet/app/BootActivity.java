package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
            return;
        }

        TextView tv = new TextView(this);
        tv.setText("STOPBET PRO\nVERSÃO 2.0.3\nBOOT OK");
        tv.setTextSize(22);
        tv.setPadding(40,40,40,40);

        setContentView(tv);

        tv.postDelayed(() -> {
            startActivity(new Intent(this, PaymentActivity.class));
            finish();
        }, 1200);
    }
}
