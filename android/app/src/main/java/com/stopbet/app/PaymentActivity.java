package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        String userId = AppPrefs.getUserId(this);

        TextView idView = new TextView(this);
        idView.setText("ID do usuário:\n" + userId);

        TextView info = new TextView(this);
        info.setText(
                "\nPagamento via Pix\n" +
                "Envie o comprovante + print desta tela\n" +
                "para liberação do app."
        );

        Button btn = new Button(this);
        btn.setText("ENTRAR (teste)");
        btn.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class))
        );

        layout.addView(title);
        layout.addView(idView);
        layout.addView(info);
        layout.addView(btn);

        setContentView(layout);
    }
}
