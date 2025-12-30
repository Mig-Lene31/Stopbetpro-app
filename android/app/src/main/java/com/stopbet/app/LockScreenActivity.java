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

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("STOPBET PRO");
        title.setTextSize(22);
        title.setGravity(Gravity.CENTER);

        // 🔐 ACESSO ADM OCULTO (5 TOQUES)
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
        info.setText(
                "🔒 ACESSO BLOQUEADO\n\n" +
                "Para liberar o uso do aplicativo:\n\n" +
                "💰 PIX PARA PAGAMENTO:\n" +
                "11 970200771\n\n" +
                "📲 ENVIE O COMPROVANTE NO WHATSAPP:\n" +
                "11 970200771\n\n" +
                "📸 Envie também o PRINT desta tela\n" +
                "com o ID do aplicativo.\n\n" +
                "🆔 ID DO USUÁRIO:\n" +
                userId
        );
        info.setGravity(Gravity.CENTER);

        Button btnAdvance = new Button(this);
        btnAdvance.setText("Avançar");
        btnAdvance.setEnabled(false); // 🔒 só ADM libera

        // Se ADM já liberou, permite avançar
        if (AdminSession.isUnlocked(this)) {
            btnAdvance.setEnabled(true);
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
