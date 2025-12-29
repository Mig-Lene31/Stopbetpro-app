package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        ScrollView s = new ScrollView(this);
        TextView t = new TextView(this);

        t.setText(
                "StopBet Pro\n\n" +
                "Aplicativo de controle de apostas.\n\n" +
                "Bloqueia automaticamente ao atingir limites.\n\n" +
                "Não incentiva apostas."
        );

        s.addView(t);
        setContentView(s);
    }
}
