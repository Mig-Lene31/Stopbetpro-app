package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
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
        title.setText("Configurar Stop Tempo (minutos)");
        title.setTextSize(20);

        EditText tempo = new EditText(this);
        tempo.setHint("Ex: 60");
        tempo.setInputType(InputType.TYPE_CLASS_NUMBER);

        int atual = TimeStore.getMinutes(this);
        if (atual > 0) {
            tempo.setText(String.valueOf(atual));
        }

        TextView status = new TextView(this);

        Button salvar = new Button(this);
        salvar.setText("SALVAR TEMPO");
        salvar.setOnClickListener(v -> {
            try {
                int minutos = Integer.parseInt(tempo.getText().toString());
                TimeStore.saveMinutes(this, minutos);
                DailyTimeEngine.reset(this); // reinicia contagem
                status.setText("✅ TEMPO SALVO COM SUCESSO");
            } catch (Exception e) {
                status.setText("❌ VALOR INVÁLIDO");
            }
        });

        Button voltar = new Button(this);
        voltar.setText("⬅ Voltar");
        voltar.setOnClickListener(v -> finish());

        layout.addView(title);
        layout.addView(tempo);
        layout.addView(salvar);
        layout.addView(status);
        layout.addView(voltar);

        setContentView(layout);
    }
}
