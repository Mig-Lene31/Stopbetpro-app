package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#F5F7F6"));
        layout.setPadding(40, 60, 40, 40);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView title = new TextView(this);
        title.setText("Painel StopBet Pro");
        title.setTextSize(22);
        title.setTextColor(Color.parseColor("#1E3A5F"));
        title.setPadding(0, 0, 0, 40);

        layout.addView(title);

        layout.addView(makeButton("Depósito", DepositActivity.class));
        layout.addView(makeButton("Stop Win / Stop Loss", LimitsActivity.class));
        layout.addView(makeButton("Tempo de Uso", TimeActivity.class));
        layout.addView(makeButton("Regras", RulesActivity.class));

        setContentView(layout);
    }

    private Button makeButton(String text, Class<?> target) {
        Button b = new Button(this);
        b.setText(text);
        b.setBackgroundColor(Color.parseColor("#2F4F3E"));
        b.setTextColor(Color.WHITE);
        b.setTextSize(16);

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        lp.setMargins(0, 0, 0, 30);
        b.setLayoutParams(lp);

        b.setOnClickListener(v ->
                startActivity(new Intent(this, target))
        );

        return b;
    }
}
