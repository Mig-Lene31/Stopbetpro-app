package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlockedActivity extends Activity {

    private TextView timerText;
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

        timerText = new TextView(this);
        timerText.setTextSize(18);

        TextView info = new TextView(this);
        info.setText(
                "Você atingiu um limite configurado.\n\n" +
                "O acesso será liberado automaticamente após 12 horas.\n\n" +
                "Para desbloquear antes, utilize o PIX:\n\n" +
                "PIX: 000.000.000-00\n" +
                "Valor: R$ XX,XX\n\n" +
                "Após o pagamento, toque em DESBLOQUEAR."
        );

        Button unlock = new Button(this);
        unlock.setText("🔓 DESBLOQUEAR");
        unlock.setOnClickListener(v ->
                startActivity(new Intent(this, UnlockActivity.class))
        );

        layout.addView(title);
        layout.addView(timerText);
        layout.addView(info);
        layout.addView(unlock);

        setContentView(layout);
        startTimer();
    }

    private void startTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long remaining = EngineState.getRemainingTime(BlockedActivity.this);

                if (remaining <= 0) {
                    EngineState.clearBlock(BlockedActivity.this);
                    startActivity(new Intent(BlockedActivity.this, MainActivity.class));
                    finish();
                    return;
                }

                long minutes = remaining / 60000;
                long seconds = (remaining % 60000) / 1000;
                timerText.setText("Tempo restante: " + minutes + "m " + seconds + "s");

                handler.postDelayed(this, 1000);
            }
        }, 0);
    }
}
