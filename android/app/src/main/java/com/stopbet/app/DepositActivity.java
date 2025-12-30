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
            "Informe o valor que pretende jogar.\n\n" +
            "⚠️ Atenção:\n" +
            "O app NÃO diferencia bônus.\n" +
            "Se houver bônus, ele deve ser somado ao valor informado."
        );
        info.setGravity(Gravity.CENTER);

        EditText input = new EditText(this);
        input.setHint("Valor do depósito (R$)");

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
