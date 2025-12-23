package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    private int tapCount = 0;
    private long lastTapTime = 0;

    private TextView statusView;
    private Button avancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("STOPBET PRO");
        title.setTextSize(22);

        String userId = AppState.getUserId(this);

        TextView idView = new TextView(this);
        idView.setText("SEU ID:\n" + userId);
        idView.setTextSize(18);

        // GESTO SECRETO: 5 toques rápidos no ID
        idView.setOnClickListener(v -> {
            long now = SystemClock.elapsedRealtime();
            if (now - lastTapTime < 800) {
                tapCount++;
            } else {
                tapCount = 1;
            }
            lastTapTime = now;

            if (tapCount == 5) {
                tapCount = 0;
                startActivity(new Intent(this, AdminActivity.class));
            }
        });

        TextView info = new TextView(this);
        info.setText(
                "Para liberar o acesso:\n\n" +
                "1️⃣ Envie o pagamento via PIX\n" +
                "2️⃣ Tire um PRINT desta tela\n" +
                "3️⃣ Envie o comprovante no WhatsApp\n\n" +
                "📲 WhatsApp / PIX:\n(11) 97020-0771\n\n" +
                "Após a confirmação, o acesso será liberado."
        );

        statusView = new TextView(this);

        avancar = new Button(this);
        avancar.setText("Avançar");
        avancar.setOnClickListener(v -> {
            if (AppStateAdmin.isReleased(this)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });

        layout.addView(title);
        layout.addView(idView);
        layout.addView(info);
        layout.addView(statusView);
        layout.addView(avancar);

        setContentView(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean liberado = AppStateAdmin.isReleased(this);

        if (liberado) {
            statusView.setText("STATUS: LIBERADO");
            avancar.setEnabled(true);
        } else {
            statusView.setText("STATUS: AGUARDANDO PAGAMENTO");
            avancar.setEnabled(false);
        }
    }
}
