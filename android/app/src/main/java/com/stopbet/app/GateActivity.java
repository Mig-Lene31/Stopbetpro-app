package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!InfoAcceptedStore.hasAccepted(this)) {
            startActivity(new Intent(this, InfoActivity.class));
            finish();
            return;
        }

        String userId = UserIdentity.getId(this);

        FirebaseAccessStore.checkAccess(this, userId, unlocked -> {
            if (unlocked) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                startActivity(new Intent(this, PaymentActivity.class));
            }
            finish();
        });
    }
}
