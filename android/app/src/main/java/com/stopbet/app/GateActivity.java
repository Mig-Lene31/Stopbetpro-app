package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userId = UserIdentity.getId(this);

        if (!InfoAcceptedStore.hasAccepted(this)) {
            startActivity(new Intent(this, InfoActivity.class));
        }
        else if (!AdminUnlockStore.isUnlocked(this, userId)) {
            startActivity(new Intent(this, PaymentActivity.class));
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }
}
