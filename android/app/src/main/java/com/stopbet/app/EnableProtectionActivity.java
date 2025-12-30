package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EnableProtectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setGravity(Gravity.CENTER);

        TextView info = new TextView(this);
        info.setText(
            "Para que o StopBet Pro funcione corretamente,\n" +
            "é necessário ativar a proteção nas configurações.\n\n" +
            "Isso permite que o app leia o saldo visível\n" +
            "e aplique os limites automaticamente."
        );
        info.setGravity(Gravity.CENTER);

        Button btn = new Button(this);
        btn.setText("Ativar proteção agora");
        btn.setOnClickListener(v ->
                startActivity(
                        new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                )
        );

        layout.addView(info);
        layout.addView(btn);

        setContentView(layout);
    }
}
