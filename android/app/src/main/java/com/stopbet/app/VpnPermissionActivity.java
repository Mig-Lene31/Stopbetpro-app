package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.widget.Toast;

public class VpnPermissionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = VpnService.prepare(this);
        if (intent != null) {
            startActivityForResult(intent, 100);
        } else {
            startVpn();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            startVpn();
        } else {
            Toast.makeText(this, "VPN obrigatória para ativar o motor", Toast.LENGTH_LONG).show();
            MotorStateStore.setRunning(this, false);
        }
        finish();
    }

    private void startVpn() {
        startService(new Intent(this, KairosVpnService.class));
    }
}
