package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        layout.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText("Defina o tempo máximo diário (minutos)");
        info.setGravity(Gravity.CENTER);

        EditText input = new EditText(this);
        input.setHint("Minutos por dia");
        input.setText(String.valueOf(TimeStore.getMinutes(this)));

        Button save = new Button(this);
        save.setText("Salvar");
        save.setOnClickListener(v -> {
            int minutes = Integer.parseInt(input.getText().toString());
            TimeStore.setMinutes(this, minutes);
            ConfigRules.onSaveTime(this);
            finish();
        });

        layout.addView(info);
        layout.addView(input);
        layout.addView(save);

        setContentView(layout);
    }
}
