package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdminActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("Painel Administrativo");
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);

        Button unlock30 = new Button(this);
        unlock30.setText("Liberar por 30 dias");
        unlock30.setOnClickListener(v -> {
            AuthState.unlockFor30Days(this);
            finish();
        });

        Button unlock12 = new Button(this);
        unlock12.setText("Quebrar bloqueio de Stop (12h)");
        unlock12.setOnClickListener(v -> {
            EngineState.clearBlock(this);
            finish();
        });

        layout.addView(title);
        layout.addView(unlock30);
        layout.addView(unlock12);

        setContentView(layout);
    }
}
