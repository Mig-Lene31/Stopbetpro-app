package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.LinearLayout;

public class BlockScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60,60,60,60);

        TextView msg = new TextView(this);
        msg.setText("Acesso bloqueado pelo Kairós\n\nIsso é para sua proteção.");
        msg.setGravity(Gravity.CENTER);
        msg.setTextSize(18);

        root.addView(msg);
        setContentView(root);
    }
}
