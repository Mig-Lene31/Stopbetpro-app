package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
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
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("ÁREA ADMINISTRATIVA");
        title.setTextSize(22);

        EditText senha = new EditText(this);
        senha.setHint("Senha administrativa");
        senha.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        EditText userId = new EditText(this);
        userId.setHint("ID DO USUÁRIO");

        TextView status = new TextView(this);

        Button liberarApp = new Button(this);
        liberarApp.setText("LIBERAR APP");

        Button desbloquearAgora = new Button(this);
        desbloquearAgora.setText("DESBLOQUEAR AGORA");

        liberarApp.setOnClickListener(v -> {
            String hoje = new SimpleDateFormat("ddMM", Locale.getDefault())
                    .format(new Date()) + "mi$";

            if (senha.getText().toString().equals(hoje)) {
                AdminUnlockStore.saveAuthorizedId(this, userId.getText().toString().trim());
                LicenseState.grant30Days(this);
                status.setText("✅ APP LIBERADO");
            } else {
                status.setText("❌ SENHA INVÁLIDA");
            }
        });

        desbloquearAgora.setOnClickListener(v -> {
            String hoje = new SimpleDateFormat("ddMM", Locale.getDefault())
                    .format(new Date()) + "mi$";

            if (senha.getText().toString().equals(hoje)) {
                EngineState.adminUnlock(this);
                status.setText("🔓 BLOQUEIO REMOVIDO");
            } else {
                status.setText("❌ SENHA INVÁLIDA");
            }
        });

        layout.addView(title);
        layout.addView(senha);
        layout.addView(userId);
        layout.addView(liberarApp);
        layout.addView(desbloquearAgora);
        layout.addView(status);

        setContentView(layout);
    }
}
