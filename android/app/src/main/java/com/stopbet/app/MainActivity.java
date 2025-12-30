package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

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
        info.setText(
            "Aplicativo de controle de apostas ativo.\n\n" +
            "Configure seus limites e ative a proteção quando desejar."
        );

        layout.addView(title);
        layout.addView(info);

        setContentView(layout);
    }
}
