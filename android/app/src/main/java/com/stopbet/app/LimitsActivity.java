package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LimitsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);

        EditText stopWin = new EditText(this);
        stopWin.setHint("Stop Win");
        stopWin.setInputType(
                InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL
        );

        EditText stopLoss = new EditText(this);
        stopLoss.setHint("Stop Loss");
        stopLoss.setInputType(
                InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL
        );

        Button save = new Button(this);
        save.setText("SALVAR");
        save.setOnClickListener(v -> {
            try {
                float win = Float.parseFloat(
                        stopWin.getText().toString().replace(",", ".")
                );
                float loss = Float.parseFloat(
                        stopLoss.getText().toString().replace(",", ".")
                );

                LimitsStore.setWin(this, win);
                LimitsStore.setLoss(this, loss);

                Toast.makeText(this, "Stops salvos", Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "Valores inválidos", Toast.LENGTH_SHORT).show();
            }
        });

        root.addView(stopWin);
        root.addView(stopLoss);
        root.addView(save);

        setContentView(root);
    }
}
