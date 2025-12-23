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
        title.setText("Tempo de Uso (minutos)");
        title.setTextSize(20);

        EditText input = new EditText(this);
        input.setHint("Ex: 60");

        Button salvar = new Button(this);
        salvar.setText("Salvar Tempo");

        TextView status = new TextView(this);

        salvar.setOnClickListener(v -> {
            try {
                int minutos = Integer.parseInt(input.getText().toString());
                AppState.setTimeLimit(this, minutos);
                status.setText("Tempo salvo: " + minutos + " min");
            } catch (Exception e) {
                status.setText("Digite um número válido");
            }
        });

        layout.addView(title);
        layout.addView(input);
        layout.addView(salvar);
        layout.addView(status);

        setContentView(layout);
    }
}
