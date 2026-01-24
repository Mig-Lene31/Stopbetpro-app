package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BlockScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String reason = getIntent().getStringExtra("reason");
        if (reason == null) reason = "Atividade bloqueada";

        TextView tv = new TextView(this);
        tv.setText(
                "🛑 Kairós ativo\n\n" +
                "Motivo:\n" + reason + "\n\n" +
                "Essa proteção foi ativada para te manter no controle."
        );
        tv.setTextSize(18f);
        tv.setPadding(40, 80, 40, 40);

        setContentView(tv);
    }
}
