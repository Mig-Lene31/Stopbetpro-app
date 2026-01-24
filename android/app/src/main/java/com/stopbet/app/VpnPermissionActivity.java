package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.widget.Toast;

public class VpnPermissionActivity extends Activity {

    private static final int VPN_REQ = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = VpnService.prepare(this);
        if (intent != null) {
            startActivityForResult(intent, VPN_REQ);
        } else {
            onVpnGranted();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VPN_REQ && resultCode == RESULT_OK) {
            onVpnGranted();
        } else {
            Toast.makeText(this, "Permissão de VPN negada", Toast.LENGTH_LONG).show();
            MotorStateStore.setRunning(this, false);
            finish();
        }
    }

    private void onVpnGranted() {
        
startForegroundService(new Intent(this, KairosVpnService.class));
new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(this::finish, 300);
        finish();
    }
}
