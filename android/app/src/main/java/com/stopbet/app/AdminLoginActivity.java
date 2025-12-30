package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
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
        layout.setPadding(40,40,40,40);
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
        error.setGravity(Gravity.CENTER);

        Button enter = new Button(this);
        enter.setText("Entrar");

        enter.setOnClickListener(v -> {
            String senha = inputPassword.getText().toString().trim();
            String id = inputId.getText().toString().trim();

            String hoje = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());
            String senhaCorreta = hoje + "Mi$";

            if (senha.equals(senhaCorreta) && id.equals(UserIdentity.getId(this))) {
                AdminSession.setUnlocked(this, true);
                startActivity(new Intent(this, AdminActivity.class));
                finish();
            } else {
                error.setText("Senha ou ID inválidos");
            }
        });

        layout.addView(title);
        layout.addView(inputPassword);
        layout.addView(inputId);
        layout.addView(enter);
        layout.addView(error);

        setContentView(layout);
    }
}
