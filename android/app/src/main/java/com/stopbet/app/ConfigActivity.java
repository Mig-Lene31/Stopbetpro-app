package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConfigActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("Configurações");
        title.setTextSize(18);
        title.setGravity(Gravity.CENTER);

        Button deposito = new Button(this);
        deposito.setText("Valor de Depósito");

        Button stopWinLoss = new Button(this);
        stopWinLoss.setText("Configurar Stop Win / Loss");

        Button stopTempo = new Button(this);
        stopTempo.setText("Configurar Tempo");

        Button info = new Button(this);
        info.setText("Informações e Uso");

        Button motor = new Button(this);
        motor.setText("Motor: DESATIVADO");

        layout.addView(title);
        layout.addView(deposito);
        layout.addView(stopWinLoss);
        layout.addView(stopTempo);
        layout.addView(info);
        layout.addView(motor);

        setContentView(layout);
    }
}
