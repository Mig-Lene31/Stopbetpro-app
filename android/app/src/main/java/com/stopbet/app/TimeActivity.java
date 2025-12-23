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
        title.setText("Configurar Tempo (min)");
        title.setTextSize(20);

        EditText tempo = new EditText(this);
        tempo.setHint("Tempo em minutos");
        tempo.setText(AppPrefs.getTime(this));

        Button salvar = new Button(this);
        salvar.setText("Salvar");
        salvar.setOnClickListener(v -> {
            AppPrefs.setTime(this, tempo.getText().toString());
            finish();
        });

        Button voltar = new Button(this);
        voltar.setText("⬅ Voltar");
        voltar.setOnClickListener(v -> finish());

        layout.addView(title);
        layout.addView(tempo);
        layout.addView(salvar);
        layout.addView(voltar);

        setContentView(layout);
    }
}
