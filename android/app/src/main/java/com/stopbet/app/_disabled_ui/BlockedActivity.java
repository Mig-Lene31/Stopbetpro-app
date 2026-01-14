package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlockedActivity extends Activity {

    private int tapCount = 0;
    private long lastTap = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(60, 60, 60, 60);
        layout.setBackgroundColor(0xFF0D1B2A);

        TextView title = new TextView(this);
        title.setText("Kairós");
        title.setTextColor(0xFFFFFFFF);
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText("Aplicativo bloqueado.\nAguarde o tempo de liberação.");
        info.setTextColor(0xFFDDDDDD);
        info.setGravity(Gravity.CENTER);

        title.setOnClickListener(v -> {
            long now = System.currentTimeMillis();

            if (now - lastTap > 1500) {
                tapCount = 0;
            }

            tapCount++;
            lastTap = now;

            if (tapCount == 5) {
                startActivity(new Intent(this, AdminHiddenActivity.class));
                tapCount = 0;
            }
        });

        layout.addView(title);
        layout.addView(info);

        setContentView(layout);
    }
}
