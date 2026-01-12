package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);
        root.setBackgroundColor(UiStyle.background());

        TextView title = new TextView(this);
        title.setText("⏱️ TEMPO DE JOGO");
        UiStyle.applyTitle(title);
        title.setGravity(Gravity.CENTER);

        EditText time = new EditText(this);
        time.setHint("Minutos (ex: 30)");
        time.setInputType(InputType.TYPE_CLASS_NUMBER);
        time.setGravity(Gravity.CENTER);
        UiStyle.applyInput(time);

        time.setText(String.valueOf(TimeStore.getMinutes(this)));

        Button save = new Button(this);
        save.setText("SALVAR TEMPO");
        save.setOnClickListener(v -> {
            int minutes = time.getText().toString().isEmpty() ? 0 : Integer.parseInt(time.getText().toString());
            TimeStore.setMinutes(this, minutes);
            finish();
        });

        Button back = new Button(this);
        back.setText("VOLTAR");
        back.setOnClickListener(v -> finish());

        root.addView(title);
        root.addView(time);
        root.addView(save);
        root.addView(back);

        setContentView(root);
    }
}
