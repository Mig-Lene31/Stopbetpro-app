package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlockedActivity extends Activity {

    private TextView timerView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("ACESSO BLOQUEADO");
        title.setTextSize(22);

        timerView = new TextView(this);
        timerView.setTextSize(18);

        layout.addView(title);
        layout.addView(timerView);

        setContentView(layout);

        atualizarTimer();
    }

    private void atualizarTimer() {
        long ms = EngineState.getRemainingMillis(this);

        if (ms <= 0) {
            EngineState.clearBlock(this);
            finish();
            return;
        }

        long s = ms / 1000;
        long h = s / 3600;
        long m = (s % 3600) / 60;
        long sec = s % 60;

        timerView.setText(
                "Tempo restante:\n" +
                String.format("%02d:%02d:%02d", h, m, sec) +
                "\n\nDesbloqueio antecipado:\nR$ 50,00"
        );

        handler.postDelayed(this::atualizarTimer, 1000);
    }

    @Override
    public void onBackPressed() {
        // bloqueia voltar
    }
}
