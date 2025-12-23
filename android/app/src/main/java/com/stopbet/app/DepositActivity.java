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
        title.setText("Configurar Depósito");
        title.setTextSize(20);

        EditText input = new EditText(this);
        input.setHint("Valor do depósito");

        Button salvar = new Button(this);
        salvar.setText("Salvar Depósito");

        TextView status = new TextView(this);

        salvar.setOnClickListener(v -> {
            try {
                float valor = Float.parseFloat(input.getText().toString());
                AppState.setDeposit(this, valor);
                status.setText("Depósito salvo: R$ " + valor);
            } catch (Exception e) {
                status.setText("Digite um valor válido");
            }
        });

        layout.addView(title);
        layout.addView(input);
        layout.addView(salvar);
        layout.addView(status);

        setContentView(layout);
    }
}
