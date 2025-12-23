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
        title.setText("Tempo de Uso (min)");
        title.setTextSize(20);

        EditText input = new EditText(this);
        input.setHint("Ex: 60");

        int saved = AppState.getTimeLimit(this);
        if (saved > 0) {
            input.setText(String.valueOf(saved));
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
                status.setText("Número inválido");
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
