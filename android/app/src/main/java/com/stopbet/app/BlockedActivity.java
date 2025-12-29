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
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("⛔ ACESSO BLOQUEADO");
        title.setTextSize(22);

        timer = new TextView(this);

        TextView info = new TextView(this);
        info.setText(
                "Limite atingido.\n\n" +
                "Bloqueio ativo por 12 horas.\n\n" +
                "PIX: (11) 97020-0771\n" +
                "Valor: R$ 50,00\n\n" +
                "Envie o comprovante ao administrador."
        );

        layout.addView(title);
        layout.addView(timer);
        layout.addView(info);

        setContentView(layout);
        iniciarTimer();
    }

    private void iniciarTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long restante = EngineState.getRemainingTime(BlockedActivity.this);

                if (restante <= 0) {
                    EngineState.autoUnlock(BlockedActivity.this);
                    startActivity(new Intent(BlockedActivity.this, MainActivity.class));
                    finish();
                    return;
                }

                long h = restante / 3600000;
                long m = (restante % 3600000) / 60000;
                long s = (restante % 60000) / 1000;

                timer.setText("Tempo restante: " + h + "h " + m + "m " + s + "s");
                handler.postDelayed(this, 1000);
            }
        }, 0);
    }
}
