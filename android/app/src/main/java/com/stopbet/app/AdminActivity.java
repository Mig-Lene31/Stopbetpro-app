package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdminActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("ÁREA ADMINISTRATIVA");
        title.setTextSize(22);

        Button liberarAcesso = new Button(this);
        liberarAcesso.setText("Liberar Avançar");
        liberarAcesso.setOnClickListener(v -> {
            AppStateAdmin.setReleased(this, true);
        });

        Button liberarMotor = new Button(this);
        liberarMotor.setText("Desbloquear Motor (R$50)");
        liberarMotor.setOnClickListener(v -> {
            EngineState.clearBlock(this);
            MotorState.setEnabled(this, false);
        });

        layout.addView(title);
        layout.addView(liberarAcesso);
        layout.addView(liberarMotor);

        setContentView(layout);
    }
}
