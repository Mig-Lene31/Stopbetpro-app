package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DepositActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("DEPÓSITO - VERSÃO 2.0.6 [PROVA]");
        title.setTextSize(20);

        EditText input = new EditText(this);
        input.setHint("Valor do depósito");

        float salvo = AppState.getDeposit(this);
        if (salvo > 0) {
            input.setText(String.valueOf(salvo));
        }

        TextView status = new TextView(this);

        Button salvar = new Button(this);
        salvar.setText("Salvar");
        salvar.setOnClickListener(v -> {
            try {
                float valor = Float.parseFloat(input.getText().toString());
                AppState.setDeposit(this, valor);
                status.setText("Salvo: R$ " + valor);
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
