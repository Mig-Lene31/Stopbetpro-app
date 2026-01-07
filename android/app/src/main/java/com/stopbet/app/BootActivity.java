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
            finish();
            return;
        }

        if (!InfoAcceptedStore.hasAccepted(this)) {
            startActivity(new Intent(this, InfoActivity.class));
            finish();
            return;
        }

        String userId = UserIdentity.getId(this);

        if (ReleaseState.isReleased(this)) {
            startActivity(new Intent(this, GateActivity.class));
            finish();
            return;
        }

        FirebaseReleaseChecker.check(userId, released -> {
            if (released) {
                ReleaseState.markReleased(this);
                startActivity(new Intent(this, GateActivity.class));
            } else {
                startActivity(new Intent(this, PaymentActivity.class));
            }
            finish();
        });
    }
}
