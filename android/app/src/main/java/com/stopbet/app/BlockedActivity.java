package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlockedActivity extends Activity {

    private Handler handler = new Handler();
    private TextView timerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(0xFF0A2A66);

        String userId = UserIdentity.getId(this);
        int reason = EngineState.getBlockReason(this);

        String reasonText = "BLOQUEIO MANUAL";
        if (reason == EngineState.REASON_STOP_WIN)  reasonText = "STOP WIN ATINGIDO";
        if (reason == EngineState.REASON_STOP_LOSS) reasonText = "STOP LOSS ATINGIDO";
        if (reason == EngineState.REASON_STOP_TIME) reasonText = "TEMPO ESGOTADO";

        TextView title = new TextView(this);
        title.setText("ACESSO BLOQUEADO");
        title.setTextSize(20);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);

        TextView reasonView = new TextView(this);
        reasonView.setText(reasonText);
        reasonView.setTextColor(0xFFFFFFFF);
        reasonView.setGravity(Gravity.CENTER);

        timerView = new TextView(this);
        timerView.setTextColor(0xFFFFFFFF);
        timerView.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText(
            "\nID DO USUÁRIO:\n" + userId +
            "\n\nDesbloqueio antecipado:\nR$ 50,00\n\n" +
            "PIX: SEU_PIX_AQUI\n" +
            "WhatsApp: SEU_WHATSAPP_AQUI\n\n" +
            "Recomendado aguardar o tempo zerar."
        );
        info.setTextColor(0xFFFFFFFF);
        info.setGravity(Gravity.CENTER);

        layout.addView(title);
        layout.addView(reasonView);
        layout.addView(timerView);
        layout.addView(info);

        setContentView(layout);

        startTimer();
    }

    private void startTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long r = EngineState.getRemainingTime(BlockedActivity.this);
                if (r <= 0) {
                    EngineState.autoUnlock(BlockedActivity.this);
                    finish();
                    return;
                }

                long s = r / 1000;
                long h = s / 3600;
                long m = (s % 3600) / 60;
                long sec = s % 60;

                timerView.setText(
                    "\nTEMPO RESTANTE\n" +
                    h + "h " + m + "m " + sec + "s"
                );

                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
}
