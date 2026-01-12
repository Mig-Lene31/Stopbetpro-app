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

        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("Ex: 150");
        input.setGravity(Gravity.CENTER);
        UiStyle.applyInput(input);

        input.setText(String.valueOf(DepositStore.get(this)));

        Button save = new Button(this);
        save.setText("SALVAR VALOR");
        save.setOnClickListener(v -> {
            float value = input.getText().toString().isEmpty() ? 0 : Float.parseFloat(input.getText().toString());
            DepositStore.set(this, value);
            AppState.resetBalance(this);
            finish();
        });

        Button back = new Button(this);
        back.setText("VOLTAR");
        back.setOnClickListener(v -> finish());

        root.addView(title);
        root.addView(input);
        root.addView(save);
        root.addView(back);

        setContentView(root);
    }
}
