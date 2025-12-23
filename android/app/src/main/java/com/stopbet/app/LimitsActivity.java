package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LimitsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("Stop Win / Stop Loss");
        title.setTextSize(20);

        EditText win = new EditText(this);
        win.setHint("Stop Win");

        EditText loss = new EditText(this);
        loss.setHint("Stop Loss");

        Button salvar = new Button(this);
        salvar.setText("Salvar Stops");

        TextView status = new TextView(this);

        salvar.setOnClickListener(v -> {
            try {
                float sw = Float.parseFloat(win.getText().toString());
                float sl = Float.parseFloat(loss.getText().toString());
                AppState.setStopWin(this, sw);
                AppState.setStopLoss(this, sl);
                status.setText("Stops salvos\nWin: " + sw + " | Loss: " + sl);
            } catch (Exception e) {
                status.setText("Valores inválidos");
            }
        });

        layout.addView(title);
        layout.addView(win);
        layout.addView(loss);
        layout.addView(salvar);
        layout.addView(status);

        setContentView(layout);
    }
}
