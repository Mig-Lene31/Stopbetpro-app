package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("TEMPO DE USO - 2.0.6");
        title.setTextSize(20);

        EditText input = new EditText(this);
        input.setHint("Minutos");

        int salvo = AppState.getTimeLimit(this);
        if (salvo > 0) {
            input.setText(String.valueOf(salvo));
        }

        TextView status = new TextView(this);

        Button salvar = new Button(this);
        salvar.setText("Salvar");
        salvar.setOnClickListener(v -> {
            try {
                int min = Integer.parseInt(input.getText().toString());
                AppState.setTimeLimit(this, min);
                status.setText("Tempo salvo: " + min + " min");
            } catch (Exception e) {
                status.setText("Valor inválido");
            }
        });

        Button voltar = new Button(this);
        voltar.setText("Voltar");
        voltar.setOnClickListener(v -> finish());

        layout.addView(title);
        layout.addView(input);
        layout.addView(salvar);
        layout.addView(status);
        layout.addView(voltar);

        setContentView(layout);
    }
}
