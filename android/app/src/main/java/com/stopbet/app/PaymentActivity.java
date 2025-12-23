package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Button;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);

        TextView info = new TextView(this);
        info.setText("Tela de pagamento\n(estrutura fase 2.1)");

        Button btn = new Button(this);
        btn.setText("Entrar (teste)");

        btn.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class))
        );

        layout.addView(title);
        layout.addView(info);
        layout.addView(btn);

        setContentView(layout);
    }
}
