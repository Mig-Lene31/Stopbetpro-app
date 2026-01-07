package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, EngineRecoveryService.class));

        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
        } else {
            startActivity(new Intent(this, GateActivity.class));
        }

        finish();
    }
}
