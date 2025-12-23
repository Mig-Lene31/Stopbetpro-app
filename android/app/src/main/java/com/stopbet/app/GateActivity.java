package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("STOPBET PRO");
        title.setTextSize(22);

        TextView id = new TextView(this);
        id.setText("ID DO USUÁRIO:\n" + AppState.getUserId(this));

        TextView status = new TextView(this);
        status.setText(
            AppState.isReleased(this)
                ? "Status: LIBERADO"
                : "Status: AGUARDANDO PAGAMENTO"
        );

        Button avancar = new Button(this);
        avancar.setText("Avançar");
        avancar.setOnClickListener(v -> {
            if (AppState.isReleased(this)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });

        layout.addView(title);
        layout.addView(id);
        layout.addView(status);
        layout.addView(avancar);

        setContentView(layout);
    }
}
