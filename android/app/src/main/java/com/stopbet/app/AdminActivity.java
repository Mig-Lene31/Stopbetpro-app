package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdminActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(40,40,40,40);

        TextView t = new TextView(this);
        t.setText("ÁREA ADMINISTRATIVA");
        t.setTextSize(22);

        EditText senha = new EditText(this);
        senha.setHint("Senha ADM");

        TextView status = new TextView(this);

        Button liberar = new Button(this);
        liberar.setText("LIBERAR ACESSO");

        liberar.setOnClickListener(v -> {
            String hoje = new SimpleDateFormat("ddMM", Locale.getDefault())
                    .format(new Date());
            String correta = hoje + "Mi$";

            if (senha.getText().toString().equals(correta)) {
                EngineState.adminUnlock(this);
                status.setText("✅ ACESSO LIBERADO MANUALMENTE");
            } else {
                status.setText("❌ SENHA INCORRETA");
            }
        });

        l.addView(t);
        l.addView(senha);
        l.addView(liberar);
        l.addView(status);

        setContentView(l);
    }
}
