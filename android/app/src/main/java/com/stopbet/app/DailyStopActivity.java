package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DailyStopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String type = DailyStopStore.getType(this);
        if (type == null) type = "STOP";

        String message =
                "⛔ Kairós encerrou seu dia\n\n" +
                "Você atingiu um limite definido por você.\n\n" +
                "Isso não é punição.\n" +
                "É proteção contra decisões impulsivas.\n\n" +
                "O acesso a apostas ficará bloqueado até amanhã.";

        TextView tv = new TextView(this);
        tv.setText(message);
        tv.setTextSize(18f);
        tv.setPadding(40, 80, 40, 40);

        setContentView(tv);
    }
}
