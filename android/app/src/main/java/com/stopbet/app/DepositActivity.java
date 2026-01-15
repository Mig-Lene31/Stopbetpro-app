package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DepositActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);

        EditText value = new EditText(this);
        value.setHint("Valor do depósito");
        value.setInputType(
                InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL
        );

        Button save = new Button(this);
        save.setText("SALVAR");
        save.setOnClickListener(v -> {
            try {
                float vlu = Float.parseFloat(
                        value.getText().toString().replace(",", ".")
                );
                DepositStore.set(this, vlu);
                Toast.makeText(this, "Depósito salvo", Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
            }
        });

        root.addView(value);
        root.addView(save);

        setContentView(root);
    }
}
