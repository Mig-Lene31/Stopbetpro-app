package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LimitsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);
        root.setBackgroundColor(UiStyle.background());

        TextView title = new TextView(this);
        title.setText("📉 LIMITES DE GANHO / PERDA");
        UiStyle.applyTitle(title);
        title.setGravity(Gravity.CENTER);

        EditText stopWin = new EditText(this);
        stopWin.setHint("Stop Win (ex: 200)");
        stopWin.setInputType(InputType.TYPE_CLASS_NUMBER);
        stopWin.setGravity(Gravity.CENTER);
        UiStyle.applyInput(stopWin);

        EditText stopLoss = new EditText(this);
        stopLoss.setHint("Stop Loss (ex: 50)");
        stopLoss.setInputType(InputType.TYPE_CLASS_NUMBER);
        stopLoss.setGravity(Gravity.CENTER);
        UiStyle.applyInput(stopLoss);

        stopWin.setText(String.valueOf(LimitsStore.getWin(this)));
        stopLoss.setText(String.valueOf(LimitsStore.getLoss(this)));

        Button save = new Button(this);
        save.setText("SALVAR LIMITES");
        save.setOnClickListener(v -> {
            float win = stopWin.getText().toString().isEmpty() ? 0 : Float.parseFloat(stopWin.getText().toString());
            float loss = stopLoss.getText().toString().isEmpty() ? 0 : Float.parseFloat(stopLoss.getText().toString());
            LimitsStore.setWin(this, win);
            LimitsStore.setLoss(this, loss);
            finish();
        });

        Button back = new Button(this);
        back.setText("VOLTAR");
        back.setOnClickListener(v -> finish());

        root.addView(title);
        root.addView(stopWin);
        root.addView(stopLoss);
        root.addView(save);
        root.addView(back);

        setContentView(root);
    }
}
