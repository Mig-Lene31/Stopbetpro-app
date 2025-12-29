package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView info = new TextView(this);
        info.setText(
                "STOPBET PRO\n\n" +
                "Este aplicativo NÃO incentiva apostas.\n\n" +
                "Ele existe para impor limites automáticos " +
                "quando o usuário perde o controle.\n\n" +
                "Funções:\n" +
                "- Stop Win / Stop Loss\n" +
                "- Stop por tempo\n" +
                "- Bloqueio automático\n\n" +
                "O usuário é totalmente responsável " +
                "por suas decisões."
        );

        layout.addView(info);
        setContentView(layout);
    }
}
