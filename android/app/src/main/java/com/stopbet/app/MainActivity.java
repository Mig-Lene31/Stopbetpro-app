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

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView tv = new TextView(this);
        tv.setText("MAIN OK\nClique para abrir Depósito");
        tv.setTextSize(18);

        Button btn = new Button(this);
        btn.setText("ABRIR DEPÓSITO");
        btn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DepositActivity.class);
            startActivity(i);
        });

        layout.addView(tv);
        layout.addView(btn);

        setContentView(layout);
    }
}
