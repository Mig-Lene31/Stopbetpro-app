package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.widget.TextView;

public class BootActivity extends Activity {

    private static final int VPN_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (EngineState.isBlocked(this)) {
            ensureVpnPermission();
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
            return;
        }

        TextView tv = new TextView(this);
        tv.setText("STOPBET PRO\nVERSÃO 2.0.3\nBOOT OK");
        tv.setTextSize(22);
        tv.setPadding(40, 40, 40, 40);
        setContentView(tv);

        tv.postDelayed(() -> {
            ensureVpnPermission();
            startActivity(new Intent(this, PaymentActivity.class));
            finish();
        }, 1200);
    }

    private void ensureVpnPermission() {
        Intent vpnIntent = VpnService.prepare(this);
        if (vpnIntent != null) {
            startActivityForResult(vpnIntent, VPN_REQUEST_CODE);
        } else if (EngineState.isBlocked(this)) {
            startService(new Intent(this, BetBlockVpnService.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VPN_REQUEST_CODE && resultCode == RESULT_OK) {
            if (EngineState.isBlocked(this)) {
                startService(new Intent(this, BetBlockVpnService.class));
            }
        }
    }
}
