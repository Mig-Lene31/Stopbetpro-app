package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50,60,50,60);
        layout.setBackgroundColor(Color.parseColor("#F5F7F6"));

        TextView title = new TextView(this);
        title.setText("StopBet Pro 2.0.3");
        title.setTextSize(24);
        title.setTextColor(Color.parseColor("#1E3A5F"));

        Button btn = new Button(this);
        btn.setText("ENTRAR");
        btn.setBackgroundColor(Color.parseColor("#2F4F3E"));
        btn.setTextColor(Color.WHITE);

        btn.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class))
        );

        layout.addView(title);
        layout.addView(btn);

        setContentView(layout);
    }
}
