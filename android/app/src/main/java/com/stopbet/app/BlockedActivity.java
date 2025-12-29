package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlockedActivity extends Activity {

    private TextView timer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        layout.setBackgroundColor(0xFF0D47A1);

        TextView title = new TextView(this);
        title.setText("⛔ ACESSO BLOQUEADO");
        title.setTextSize(24);
        title.setTextColor(0xFFFFFFFF);

        timer = new TextView(this);
        timer.setTextColor(0xFFFFFFFF);

        TextView info = new TextView(this);
        info.setTextColor(0xFFFFFFFF);
        info.setText(
                "Bloqueio ativo por 12 horas.\n\n" +
                "PIX: (11) 97020-0771\n" +
                "Valor: R$ 50,00\n\n" +
                "Envie o comprovante + ID ao administrador."
        );

        layout.addView(title);
        layout.addView(timer);
        layout.addView(info);

        setContentView(layout);
        iniciar();
    }

    private void iniciar() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long r = EngineState.getRemainingTime(BlockedActivity.this);

                if (r <= 0) {
                    EngineState.autoUnlock(BlockedActivity.this);
                    startActivity(new Intent(BlockedActivity.this, GateActivity.class));
                    finish();
                    return;
                }

                long h = r / 3600000;
                long m = (r % 3600000) / 60000;
                long s = (r % 60000) / 1000;

                timer.setText("Tempo restante: " + h + "h " + m + "m " + s + "s");
                handler.postDelayed(this, 1000);
            }
        }, 0);
    }
}
