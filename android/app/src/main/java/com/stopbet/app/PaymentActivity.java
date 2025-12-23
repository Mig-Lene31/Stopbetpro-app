package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#F5F7F6"));
        layout.setPadding(50, 60, 50, 60);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(24);
        title.setTextColor(Color.parseColor("#1E3A5F"));
        title.setPadding(0, 0, 0, 30);

        TextView info = new TextView(this);
        info.setText(
                "Pagamento via Pix\n\n" +
                "Envie o comprovante + print desta tela\n" +
                "para liberação do app."
        );
        info.setTextSize(16);
        info.setTextColor(Color.parseColor("#1C1C1C"));
        info.setPadding(0, 0, 0, 40);

        Button btn = new Button(this);
        btn.setText("ENTRAR");
        btn.setBackgroundColor(Color.parseColor("#2F4F3E"));
        btn.setTextColor(Color.WHITE);
        btn.setPadding(20, 20, 20, 20);

        btn.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class))
        );

        layout.addView(title);
        layout.addView(info);
        layout.addView(btn);

        setContentView(layout);
    }
}
