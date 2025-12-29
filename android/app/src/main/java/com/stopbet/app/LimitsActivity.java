package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LimitsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("STOP WIN / STOP LOSS");
        title.setTextSize(22);

        EditText winInput = new EditText(this);
        winInput.setHint("Stop Win (ex: 100)");
        winInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        EditText lossInput = new EditText(this);
        lossInput.setHint("Stop Loss (ex: -100)");
        lossInput.setInputType(
                InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_SIGNED
        );

        TextView status = new TextView(this);

        Button salvar = new Button(this);
        salvar.setText("SALVAR LIMITES");

        salvar.setOnClickListener(v -> {
            try {
                float win = Float.parseFloat(winInput.getText().toString());
                float loss = Float.parseFloat(lossInput.getText().toString());

                LimitsStore.saveWin(this, win);
                LimitsStore.saveLoss(this, loss);

                status.setText("✅ LIMITES SALVOS COM SUCESSO");
            } catch (Exception e) {
                status.setText("❌ VALORES INVÁLIDOS");
            }
        });

        layout.addView(title);
        layout.addView(winInput);
        layout.addView(lossInput);
        layout.addView(salvar);
        layout.addView(status);

        setContentView(layout);
    }
}
