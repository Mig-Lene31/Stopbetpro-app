package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LockScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        TextView reasonView = findViewById(R.id.lock_reason);
        TextView timeView = findViewById(R.id.lock_time);

        int reason = EngineState.getBlockReason(this);

        String msg;
        switch (reason) {
            case EngineState.REASON_STOP_WIN:
                msg = "Bloqueio por ganho excessivo";
                break;
            case EngineState.REASON_STOP_LOSS:
                msg = "Bloqueio por perda excessiva";
                break;
            case EngineState.REASON_STOP_TIME:
                msg = "Bloqueio por tempo excedido";
                break;
            default:
                msg = "Bloqueio ativo";
        }

        reasonView.setText(msg);

        long remaining = EngineState.getRemainingTime(this);
        long minutes = remaining / 60000;
        timeView.setText("Tempo restante: " + minutes + " min");
    }
}
