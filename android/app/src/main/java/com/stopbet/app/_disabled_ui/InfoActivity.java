package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!AppProtectionState.canAppRun(this)) {
            finish();
            return;
        }

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(60, 60, 60, 60);
        root.setGravity(Gravity.CENTER);

        TextView text = new TextView(this);
        text.setText("Este aplicativo ajuda a proteger você contra perdas em apostas.\n\nLeia com atenção antes de continuar.");
        text.setGravity(Gravity.CENTER);

        Button accept = new Button(this);
        accept.setText("ACEITO E QUERO CONTINUAR");
        accept.setOnClickListener(v -> {
            AppProtectionState.setInfoAccepted(this, true);
            startActivity(new Intent(this, GateActivity.class));
            finish();
        });

        root.addView(text);
        root.addView(accept);
        setContentView(root);
    }
}
