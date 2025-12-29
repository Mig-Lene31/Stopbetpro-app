package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    private int tapCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
            return;
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("STOPBET PRO");
        title.setTextSize(22);

        TextView idView = new TextView(this);
        idView.setText("SEU ID:\n" + UserIdentity.getId(this));

        TextView info = new TextView(this);
        info.setText(
                "Para liberar o acesso:\n\n" +
                "1️⃣ Envie o pagamento via PIX\n" +
                "2️⃣ Tire um PRINT desta tela\n" +
                "3️⃣ Envie o comprovante no WhatsApp\n\n" +
                "📱 WhatsApp / PIX:\n(11) 97020-0771"
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

        title.setOnClickListener(v -> {
            tapCount++;
            if (tapCount == 5) {
                startActivity(new Intent(this, AdminActivity.class));
                tapCount = 0;
            }
        });

        layout.addView(title);
        layout.addView(idView);
        layout.addView(info);
        layout.addView(status);
        layout.addView(avancar);

        setContentView(layout);
    }
}
