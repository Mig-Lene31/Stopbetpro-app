package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class BlockActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("🚫 ACESSO BLOQUEADO\n\nVolte em 12 horas.");
        tv.setTextSize(22);
        tv.setPadding(40, 200, 40, 40);

        setContentView(tv);
    }

    // ⛔ Bloqueia botão voltar
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
