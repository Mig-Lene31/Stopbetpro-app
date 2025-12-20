package com.stopbet.app;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.stopbet.app.blocker.BlockController;

public class MainActivity extends ReactActivity {

    @Override
    protected String getMainComponentName() {
        return "StopBetPro";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 🔒 BLOQUEIO IMEDIATO NA ABERTURA
        if (BlockController.isBlocked()) {
            BlockController.forceBlock(this, "BLOCK_ACTIVE");
        }
    }
}
