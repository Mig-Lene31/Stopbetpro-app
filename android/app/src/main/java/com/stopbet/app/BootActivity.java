package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setBackgroundColor(0xFF0D1B2A);

        TextView loading = new TextView(this);
        loading.setText("Verificando acesso...");
        loading.setTextSize(18);
        loading.setTypeface(null, Typeface.BOLD);
        loading.setTextColor(0xFFFFFFFF);
        loading.setGravity(Gravity.CENTER);

        root.addView(loading);
        setContentView(root);

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
