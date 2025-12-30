package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
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
        layout.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText(
            "💰 VALOR DO DEPÓSITO\n\n" +
            "Informe o valor TOTAL que pretende jogar.\n\n" +
            "⚠️ Atenção:\n" +
            "O aplicativo NÃO diferencia valor real de bônus.\n\n" +
            "Se houver bônus, some o valor do bônus\n" +
            "ao valor depositado e informe o TOTAL.\n\n" +
            "Exemplo:\n" +
            "Depósito R$100 + bônus R$50 → informe R$150"
        );
        info.setGravity(Gravity.CENTER);

        EditText input = new EditText(this);
        input.setHint("Valor total (R$)");

        Button save = new Button(this);
        save.setText("Salvar valor");
        save.setOnClickListener(v -> {
            DepositStore.setValue(this, input.getText().toString());
            finish();
        });

        layout.addView(info);
        layout.addView(input);
        layout.addView(save);

        setContentView(layout);
    }
}
