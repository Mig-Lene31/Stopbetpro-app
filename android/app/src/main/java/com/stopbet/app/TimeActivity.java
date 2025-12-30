package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("Stop por Tempo");
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText(
                "Ao ativar, o app bloqueará automaticamente\n" +
                "os sites de aposta após o tempo configurado.\n\n" +
                "O bloqueio dura 12 horas."
        );
        info.setGravity(Gravity.CENTER);

        Button btnActivate = new Button(this);
        btnActivate.setText("Ativar bloqueio por tempo");

        btnActivate.setOnClickListener(v -> {
            // grava estado de bloqueio por tempo
            TimeStore.setActive(this, true);
            EngineState.blockFor12Hours(this);

            // vai direto para a tela azul
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
        });

        layout.addView(title);
        layout.addView(info);
        layout.addView(btnActivate);

        setContentView(layout);
    }
}
