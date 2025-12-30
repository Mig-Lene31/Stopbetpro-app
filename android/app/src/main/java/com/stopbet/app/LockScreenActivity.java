package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LockScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setGravity(Gravity.CENTER);

        String userId = UserIdentity.getId(this);

        TextView title = new TextView(this);
        title.setText("STOPBET PRO");
        title.setTextSize(22);
        title.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText(
                "Para ativar o aplicativo, envie o comprovante PIX\n" +
                "junto com um PRINT desta tela mostrando o ID.\n\n" +
                "ID do usuário:\n" + userId
        );
        info.setGravity(Gravity.CENTER);

        Button btnAdvance = new Button(this);
        btnAdvance.setText("Avançar");
        btnAdvance.setEnabled(false); // 🔒 BLOQUEADO POR PADRÃO

        // 🔓 Só libera se ADM desbloquear
        if (AdminSession.isUnlocked(this)) {
            btnAdvance.setEnabled(true);
            btnAdvance.setOnClickListener(v -> {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            });
        }

        // 🔐 Acesso oculto ADM (5 toques no título)
        title.setOnClickListener(new android.view.View.OnClickListener() {
            int taps = 0;
            long lastTap = 0;

            @Override
            public void onClick(android.view.View v) {
                long now = System.currentTimeMillis();
                if (now - lastTap > 1500) taps = 0;
                lastTap = now;
                taps++;

                if (taps >= 5) {
                    taps = 0;
                    startActivity(new Intent(LockScreenActivity.this, AdminLoginActivity.class));
                }
            }
        });

        layout.addView(title);
        layout.addView(info);
        layout.addView(btnAdvance);

        setContentView(layout);
    }
}
