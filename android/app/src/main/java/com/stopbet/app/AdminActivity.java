package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class AdminActivity extends Activity {

    private String senhaDoDia() {
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return dia + "Mi$";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView titulo = new TextView(this);
        titulo.setText("ÁREA ADMINISTRATIVA");
        titulo.setTextSize(20);

        EditText senha = new EditText(this);
        senha.setHint("Senha do dia");

        EditText userId = new EditText(this);
        userId.setHint("ID do usuário");

        Button liberar = new Button(this);
        liberar.setText("Liberar acesso");

        TextView status = new TextView(this);

        liberar.setOnClickListener(v -> {
            if (!senha.getText().toString().equals(senhaDoDia())) {
                status.setText("Senha incorreta");
                return;
            }

            String id = userId.getText().toString().trim();
            if (id.length() < 4) {
                status.setText("ID inválido");
                return;
            }

            AppStateAdmin.releaseById(this, id);
            status.setText("USUÁRIO LIBERADO COM SUCESSO");
        });

        layout.addView(titulo);
        layout.addView(senha);
        layout.addView(userId);
        layout.addView(liberar);
        layout.addView(status);

        setContentView(layout);
    }
}
