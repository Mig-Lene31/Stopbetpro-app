package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
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
        title.setText("Configurar Win / Loss");
        title.setTextSize(20);

        EditText win = new EditText(this);
        win.setHint("Stop Win (ex: 50)");

        EditText loss = new EditText(this);
        loss.setHint("Stop Loss (ex: -30)");

        // carregar valores salvos
        win.setText(AppPrefs.getWin(this));
        loss.setText(AppPrefs.getLoss(this));

        Button salvar = new Button(this);
        salvar.setText("Salvar");
        salvar.setOnClickListener(v -> {
            AppPrefs.setWin(this, win.getText().toString());
            AppPrefs.setLoss(this, loss.getText().toString());
            finish();
        });

        Button voltar = new Button(this);
        voltar.setText("⬅ Voltar");
        voltar.setOnClickListener(v -> finish());

        layout.addView(title);
        layout.addView(win);
        layout.addView(loss);
        layout.addView(salvar);
        layout.addView(voltar);

        setContentView(layout);
    }
}
