package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AccessibilityRequiredActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView msg = new TextView(this);
        msg.setText(
            "⚠️ Para o StopBet funcionar corretamente, você PRECISA ativar o serviço de Acessibilidade.\n\n" +
            "Isso é necessário para bloquear sites de apostas automaticamente."
        );

        Button abrir = new Button(this);
        abrir.setText("ATIVAR ACESSIBILIDADE");
        abrir.setOnClickListener(v ->
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        );

        layout.addView(msg);
        layout.addView(abrir);

        setContentView(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AccessibilityUtil.isServiceEnabled(this)) {
            finish();
        }
    }
}
