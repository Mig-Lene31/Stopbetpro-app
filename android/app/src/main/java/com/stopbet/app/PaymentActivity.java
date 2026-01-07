package com.stopbet.app;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userId = UserIdentity.getId(this);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(50, 50, 50, 50);
        root.setBackgroundColor(0xFF0D1B2A);

        TextView title = new TextView(this);
        title.setText("Kairós — Liberação de Acesso");
        title.setTextSize(26);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);

        TextView status = new TextView(this);
        status.setText("STATUS: AGUARDANDO LIBERAÇÃO");
        status.setTextSize(18);
        status.setTextColor(0xFFFFC107);
        status.setGravity(Gravity.CENTER);

        TextView id = new TextView(this);
        id.setText("SEU ID ÚNICO:\n" + userId);
        id.setTextSize(18);
        id.setTextColor(0xFFFFFFFF);
        id.setGravity(Gravity.CENTER);

        root.addView(title);
        root.addView(status);
        root.addView(id);

        setContentView(root);
    }
}
