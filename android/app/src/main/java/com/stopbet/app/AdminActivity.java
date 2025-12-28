package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends Activity {

    private static final String ADMIN_PASSWORD = "1234"; // depois você muda

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("Modo Administrador");
        title.setTextSize(20);

        EditText senha = new EditText(this);
        senha.setHint("Senha ADM");

        Button entrar = new Button(this);
        entrar.setText("Entrar");

        layout.addView(title);
        layout.addView(senha);
        layout.addView(entrar);

        setContentView(layout);

        entrar.setOnClickListener(v -> {
            if (!ADMIN_PASSWORD.equals(senha.getText().toString())) {
                Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                return;
            }
            abrirPainel();
        });
    }

    private void abrirPainel() {

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView info = new TextView(this);
        info.setText("ID do usuário:\n" + android.os.Build.SERIAL);

        Button desbloquear12h = new Button(this);
        desbloquear12h.setText("🔓 Desbloquear por 12h");

        Button liberar30d = new Button(this);
        liberar30d.setText("✅ Liberar por 30 dias");

        desbloquear12h.setOnClickListener(v -> {
            EngineState.unlock(this);
            Toast.makeText(this, "Desbloqueado por 12h", Toast.LENGTH_SHORT).show();
        });

        liberar30d.setOnClickListener(v -> {
            AppStateAdmin.releaseFor30Days(this);
            Toast.makeText(this, "Liberado por 30 dias", Toast.LENGTH_SHORT).show();
        });

        layout.addView(info);
        layout.addView(desbloquear12h);
        layout.addView(liberar30d);

        setContentView(layout);
    }
}
