package com.stopbet.app;

import android.app.Activity;
import android.graphics.Color;
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
        layout.setPadding(40, 40, 40, 40);
        layout.setBackgroundColor(Color.parseColor("#0D47A1"));

        TextView title = new TextView(this);
        title.setText("ACESSO BLOQUEADO");
        title.setTextSize(24);
        title.setTextColor(Color.WHITE);

        timerView = new TextView(this);
        timerView.setTextSize(18);
        timerView.setTextColor(Color.WHITE);

        TextView info = new TextView(this);
        info.setTextSize(16);
        info.setTextColor(Color.WHITE);
        info.setText(
                "\nVocê atingiu um limite configurado.\n\n" +
                "Para sua proteção, o uso foi bloqueado.\n\n" +
                "⏳ O acesso será liberado automaticamente\n" +
                "após o tempo regressivo.\n\n" +
                "🔓 Desbloqueio antecipado:\n" +
                "R$ 50,00\n\n" +
                "📲 WhatsApp / Pix:\n" +
                "11 97020-0771\n\n" +
                "Envie o comprovante pelo WhatsApp."
        );

        layout.addView(title);
        layout.addView(timerView);
        layout.addView(info);

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
                "\nTempo restante:\n" +
                String.format("%02d:%02d:%02d", h, m, sec)
        );

        handler.postDelayed(this::atualizarTimer, 1000);
    }

    @Override
    public void onBackPressed() {
        // botão voltar bloqueado
    }
}
