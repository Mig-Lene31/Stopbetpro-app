package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdminActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        EditText senha = findViewById(R.id.inputSenha);

        findViewById(R.id.btnEntrar).setOnClickListener(v -> {
            String input = senha.getText().toString();

            String hoje = new SimpleDateFormat("ddMMyyyy", Locale.getDefault())
                    .format(new Date());

            String correta = hoje + "Mi$";

            if (input.equals(correta)) {
                AppStorage.activateMonthly(this);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
