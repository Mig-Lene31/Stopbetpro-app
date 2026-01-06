package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("StopBet Pro");
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);

        Button config = new Button(this);
        config.setText("Configurações");
        config.setOnClickListener(v ->
            startActivity(new Intent(this, ConfigActivity.class))
        );

        layout.addView(title);
        layout.addView(config);

        setContentView(layout);
    }
}
