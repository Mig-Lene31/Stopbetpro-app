package com.stopbet.app;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends SafeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        safeSetContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.txtMain);
        tv.setText("StopBet Pro - Estrutura OK");
    }
}
