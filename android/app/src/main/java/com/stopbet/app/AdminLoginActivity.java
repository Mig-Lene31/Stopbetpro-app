package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdminLoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("Acesso Administrativo");
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);

        EditText inputPassword = new EditText(this);
        inputPassword.setHint("Senha ADM");
        inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        EditText inputId = new EditText(this);
        inputId.setHint("ID do usuário");

        TextView error = new TextView(this);
        error.setTextColor(0xFFFF0000);
        error.setGravity(Gravity.CENTER);

        Button btnEnter = new Button(this);
        btnEnter.setText("Entrar");

        btnEnter.setOnClickListener(v -> {
            String senhaDigitada = inputPassword.getText().toString().trim();
            String idDigitado = inputId.getText().toString().trim();

            String dataHoje = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());
            String senhaCorreta = dataHoje + "Mi$";

            String idReal = UserIdentity.getId(this);

            if (senhaDigitada.equals(senhaCorreta) && idDigitado.equals(idReal)) {
                AdminSession.setUnlocked(this, true);
                finish();
            } else {
                error.setText("Senha ou ID inválidos");
            }
        });

        layout.addView(title);
        layout.addView(inputPassword);
        layout.addView(inputId);
        layout.addView(btnEnter);
        layout.addView(error);

        setContentView(layout);
    }
}
