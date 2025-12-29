package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    private int tapCount = 0;
    private long lastTapTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Se estiver bloqueado, vai pra tela azul
        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
            return;
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        // 🔒 LOCAL SECRETO DO ADM (5 TOQUES)
        TextView title = new TextView(this);
        title.setText("STOPBET PRO");
        title.setTextSize(22);

        title.setOnClickListener(v -> {
            long now = System.currentTimeMillis();

            // reseta se demorou demais
            if (now - lastTapTime > 2000) {
                tapCount = 0;
            }

            tapCount++;
            lastTapTime = now;

            if (tapCount == 5) {
                tapCount = 0;
                startActivity(new Intent(this, AdminActivity.class));
            }
        });

        TextView idView = new TextView(this);
        idView.setText("SEU ID:\n" + UserIdentity.getId(this));

        TextView info = new TextView(this);
        info.setText(
                "Para liberar o acesso:\n\n" +
                "1️⃣ Envie o pagamento via PIX\n" +
                "2️⃣ Tire um PRINT desta tela\n" +
                "3️⃣ Envie o comprovante + ID\n\n" +
                "📱 PIX / WhatsApp:\n(11) 97020-0771"
        );

        TextView status = new TextView(this);
        status.setText(
                LicenseState.isValid(this)
                        ? "STATUS: LIBERADO ✅"
                        : "STATUS: BLOQUEADO 🔒"
        );

        Button avancar = new Button(this);
        avancar.setText("Avançar");
        avancar.setEnabled(LicenseState.isValid(this));
        avancar.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        layout.addView(title);
        layout.addView(idView);
        layout.addView(info);
        layout.addView(status);
        layout.addView(avancar);

        setContentView(layout);
    }
}
