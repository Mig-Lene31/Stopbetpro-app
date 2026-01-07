package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
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
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("Configurar Stop Win / Loss");
        title.setGravity(Gravity.CENTER);

        EditText inputWin = new EditText(this);
        inputWin.setHint("Stop Win (R$)");
        inputWin.setText(String.valueOf(LimitsStore.getWin(this)));

        EditText inputLoss = new EditText(this);
        inputLoss.setHint("Stop Loss (R$)");
        inputLoss.setText(String.valueOf(LimitsStore.getLoss(this)));

        Button save = new Button(this);
        save.setText("Salvar");
        save.setOnClickListener(v -> {
            float win = Float.parseFloat(inputWin.getText().toString());
            float loss = Float.parseFloat(inputLoss.getText().toString());

            LimitsStore.saveWin(this, win);
            LimitsStore.saveLoss(this, loss);
            ConfigRules.onSaveLimits(this);
            finish();
        });

        layout.addView(title);
        layout.addView(inputWin);
        layout.addView(inputLoss);
        layout.addView(save);

        setContentView(layout);
    }
}
