package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DepositActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);
        root.setBackgroundColor(UiStyle.background());

        TextView title = new TextView(this);
        title.setText("💰 VALOR DO DEPÓSITO");
        UiStyle.applyTitle(title);
        title.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText(
                "\nInforme o valor TOTAL que pretende jogar.\n\n" +
                "⚠️ O app NÃO diferencia valor real de bônus.\n\n" +
                "Exemplo:\nDepósito R$100 + bônus R$50 → informe R$150"
        );
        UiStyle.applyText(info);
        info.setGravity(Gravity.CENTER);

        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("Ex: 150");
        input.setGravity(Gravity.CENTER);
        UiStyle.applyInput(input);

        Button save = new Button(this);
        save.setText("SALVAR VALOR");

        root.addView(title);
        root.addView(info);
        root.addView(input);
        root.addView(save);

        setContentView(root);
    }
}
