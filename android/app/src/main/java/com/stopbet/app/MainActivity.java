package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!LicenseState.isValid(this)) {
            startActivity(new Intent(this, GateActivity.class));
            finish();
            return;
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(22);

        Button motor = new Button(this);
        motor.setText("Ativar / Desativar Motor");
        motor.setOnClickListener(v ->
                MotorState.setEnabled(this, !MotorState.isEnabled(this))
        );

        Button limites = new Button(this);
        limites.setText("Stop Win / Loss");
        limites.setOnClickListener(v ->
                startActivity(new Intent(this, LimitsActivity.class))
        );

        Button tempo = new Button(this);
        tempo.setText("Stop Tempo");
        tempo.setOnClickListener(v ->
                startActivity(new Intent(this, TimeActivity.class))
        );

        layout.addView(title);
        layout.addView(motor);
        layout.addView(limites);
        layout.addView(tempo);

        setContentView(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!LicenseState.isValid(this)) {
            startActivity(new Intent(this, GateActivity.class));
            finish();
        }
    }
}
