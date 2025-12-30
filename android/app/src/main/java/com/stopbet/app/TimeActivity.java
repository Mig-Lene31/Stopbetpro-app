package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
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
        info.setText(
            "Defina quantos minutos por dia pretende jogar.\n\n" +
            "⏱ Máximo permitido: 360 minutos (6 horas).\n" +
            "Ao atingir o limite, os sites serão bloqueados por 12 horas."
        );
        info.setGravity(Gravity.CENTER);

        EditText input = new EditText(this);
        input.setHint("Minutos por dia");

        Button activate = new Button(this);
        activate.setText("Ativar Stop por Tempo");

        activate.setOnClickListener(v -> {
            int minutes = Integer.parseInt(input.getText().toString());
            if (minutes > 360) minutes = 360;

            TimeStore.setMinutes(this, minutes);
            EngineState.blockFor12Hours(this);
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
        });

        layout.addView(info);
        layout.addView(input);
        layout.addView(activate);

        setContentView(layout);
    }
}
