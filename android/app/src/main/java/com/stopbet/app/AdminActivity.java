package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdminActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("ADMIN - Desbloqueio");
        title.setGravity(Gravity.CENTER);

        EditText inputId = new EditText(this);
        inputId.setHint("ID do usuário");

        Button unlock30 = new Button(this);
        unlock30.setText("Liberar 30 dias");
        unlock30.setOnClickListener(v ->
            AdminUnlockStore.unlockForMillis(
                this,
                inputId.getText().toString().trim(),
                30L * 24L * 60L * 60L * 1000L
            )
        );

        Button unlock12h = new Button(this);
        unlock12h.setText("Liberar 12 horas");
        unlock12h.setOnClickListener(v ->
            AdminUnlockStore.unlockForMillis(
                this,
                inputId.getText().toString().trim(),
                12L * 60L * 60L * 1000L
            )
        );

        layout.addView(title);
        layout.addView(inputId);
        layout.addView(unlock30);
        layout.addView(unlock12h);

        setContentView(layout);
    }
}
