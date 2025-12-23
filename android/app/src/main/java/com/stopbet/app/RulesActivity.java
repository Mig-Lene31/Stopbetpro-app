package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RulesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText(
                "Regras e Informações\n\n" +
                "- O app não diferencia saldo real de bônus\n" +
                "- Stops são baseados no valor exibido\n" +
                "- Uso consciente é essencial"
        );
        tv.setTextSize(16);
        tv.setPadding(40, 40, 40, 40);

        setContentView(tv);
    }
}
