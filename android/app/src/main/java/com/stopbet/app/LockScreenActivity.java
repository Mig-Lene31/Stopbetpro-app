package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LockScreenActivity extends Activity {

    private int tapCount = 0;
    private long lastTap = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userId = UserIdentity.getId(this);
        String reason = EngineState.getBlockReason(this);
        String motivo;

        switch (reason) {
            case EngineState.REASON_STOP_WIN:
                motivo = "🟢 Stop Win atingido";
                break;
            case EngineState.REASON_STOP_LOSS:
                motivo = "🔴 Stop Loss atingido";
                break;
            case EngineState.REASON_STOP_TIME:
                motivo = "⏱️ Tempo diário esgotado";
                break;
            default:
                motivo = "🔒 Acesso bloqueado";
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("STOPBET PRO");
        title.setTextSize(22);
        title.setGravity(Gravity.CENTER);

        // 🔐 ADM oculto (5 toques)
        title.setOnClickListener(v -> {
            long now = System.currentTimeMillis();
            if (now - lastTap > 1500) tapCount = 0;
            lastTap = now;
            tapCount++;

            if (tapCount >= 5) {
                tapCount = 0;
                startActivity(new Intent(this, AdminLoginActivity.class));
            }
        });

        TextView info = new TextView(this);
        info.setGravity(Gravity.CENTER);
        info.setText(
                motivo + "\n\n" +
                "⚠️ Para desbloquear antes das 12h:\n" +
                "R$ 50,00\n\n" +
                "💰 PIX: 11 970200771\n" +
                "📲 WhatsApp: 11 970200771\n\n" +
                "📸 Envie o comprovante + print desta tela\n\n" +
                "🆔 ID DO USUÁRIO:\n" +
                userId
        );

        Button btnAdvance = new Button(this);
        btnAdvance.setText("Avançar");
        btnAdvance.setEnabled(AuthState.isUnlocked(this));

        if (AuthState.isUnlocked(this)) {
            btnAdvance.setOnClickListener(v ->
                    startActivity(new Intent(this, MainActivity.class))
            );
        }

        layout.addView(title);
        layout.addView(info);
        layout.addView(btnAdvance);

        setContentView(layout);
    }
}
