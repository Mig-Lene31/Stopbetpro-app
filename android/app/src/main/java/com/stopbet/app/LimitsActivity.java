package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LimitsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        AppState.setStopWin(this, 100f);
        AppState.setStopLoss(this, 50f);

        TextView tv = new TextView(this);
        tv.setText(
                "STOP CONFIGURADO\n\n" +
                "Stop Win: " + AppState.getStopWin(this) + "\n" +
                "Stop Loss: " + AppState.getStopLoss(this)
        );
        tv.setTextSize(18);

        layout.addView(tv);
        setContentView(layout);
    }
}
