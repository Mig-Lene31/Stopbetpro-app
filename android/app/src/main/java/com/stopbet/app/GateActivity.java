package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("STOPBET PRO");
        title.setTextSize(22);

        TextView idView = new TextView(this);
        idView.setText("ID DO USUÁRIO:\n" + UserIdentity.getId(this));

        TextView status = new TextView(this);

        Button entrar = new Button(this);
        entrar.setText("ENTRAR");
        entrar.setEnabled(false);
        entrar.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        Button admin = new Button(this);
        admin.setText("ADM");
        admin.setOnClickListener(v ->
                startActivity(new Intent(this, AdminActivity.class))
        );

        if (LicenseState.isValid(this)) {
            status.setText("LICENÇA ATIVA ✅");
            entrar.setEnabled(true);
        } else {
            status.setText("LICENÇA BLOQUEADA 🔒");
            entrar.setEnabled(false);
        }

        layout.addView(title);
        layout.addView(idView);
        layout.addView(status);
        layout.addView(entrar);
        layout.addView(admin);

        setContentView(layout);
    }
}
