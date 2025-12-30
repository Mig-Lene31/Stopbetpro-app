package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AuthLockActivity extends Activity {

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
                "💰 PARA USAR O APP\n\n" +
                "Valor: R$ 100,00\n\n" +
                "PIX: 11 970200771\n" +
                "WhatsApp: 11 970200771\n\n" +
                "📸 Envie o comprovante + PRINT desta tela\n\n" +
                "ID DO USUÁRIO:\n" + userId
        );
        info.setGravity(Gravity.CENTER);

        Button advance = new Button(this);
        advance.setText("Avançar");
        advance.setEnabled(false);

        if (AuthState.isAuthorized(this)) {
            advance.setEnabled(true);
            advance.setOnClickListener(v ->
                    startActivity(new Intent(this, MainActivity.class))
            );
        }

        layout.addView(title);
        layout.addView(info);
        layout.addView(advance);

        setContentView(layout);
    }
}
