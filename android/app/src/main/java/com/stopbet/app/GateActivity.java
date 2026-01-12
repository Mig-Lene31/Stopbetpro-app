package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppState.resetBalance(this);

        startService(new Intent(this, EngineService.class));
        startService(new Intent(this, StopHeartService.class));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);
        root.setBackgroundColor(0xFF0D1B2A);

        TextView title = new TextView(this);
        title.setText("Kairós");
        title.setTextColor(0xFFFFFFFF);
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);

        Button config = new Button(this);
        config.setText("CONFIGURAÇÕES");
        config.setOnClickListener(v ->
                startActivity(new Intent(this, ConfigActivity.class))
        );

        root.addView(title);
        root.addView(config);

        setContentView(root);
    }
}
