package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);
        root.setBackgroundColor(0xFF0D1B2A);

        TextView title = new TextView(this);
        title.setText("Kairós");
        title.setTextSize(32);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);

        TextView subtitle = new TextView(this);
        subtitle.setText("Sistema de proteção inteligente\npara controle de apostas");
        subtitle.setTextSize(18);
        subtitle.setTextColor(0xFFB0BEC5);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 30, 0, 60);

        Button btn = new Button(this);
        btn.setText("ENTRAR");
        btn.setTextSize(18);
        btn.setBackgroundColor(0xFF2E7D32);
        btn.setTextColor(0xFFFFFFFF);

        btn.setOnClickListener(v -> {
            startActivity(new Intent(this, ConfigActivity.class));
            finish();
        });

        root.addView(title);
        root.addView(subtitle);
        root.addView(btn);

        setContentView(root);
    }
}
