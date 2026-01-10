package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthManager.ensureAuth();

        if (!InfoAcceptedStore.hasAccepted(this)) {
            startActivity(new Intent(this, InfoActivity.class));
            finish();
            return;
        }

        if (ReleaseState.isReleased(this)) {
            startActivity(new Intent(this, GateActivity.class));
            finish();
            return;
        }

        startActivity(new Intent(this, PaymentActivity.class));
        finish();
    }
}
