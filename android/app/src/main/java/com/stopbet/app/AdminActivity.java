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

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("ADMINISTRADOR");
        title.setTextSize(22);

        EditText senha = new EditText(this);
        senha.setHint("Senha ADM");

        TextView status = new TextView(this);

        Button liberar = new Button(this);
        liberar.setText("LIBERAR 30 DIAS");

        liberar.setOnClickListener(v -> {
            String hoje = new SimpleDateFormat("ddMM", Locale.getDefault()).format(new Date());
            String correta = hoje + "Mi$";

            if (senha.getText().toString().equals(correta)) {
                LicenseState.grant30Days(this);
                status.setText("LIBERAÇÃO CONCLUÍDA ✅");
            } else {
                status.setText("SENHA INCORRETA ❌");
            }
        });

        layout.addView(title);
        layout.addView(senha);
        layout.addView(liberar);
        layout.addView(status);

        setContentView(layout);
    }
}
