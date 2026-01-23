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
            startVpnSafely();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            startVpnSafely();
        } else {
            Toast.makeText(this, "Permissão de VPN negada", Toast.LENGTH_LONG).show();
            MotorStateStore.setRunning(this, false);
            finish();
        }
    }

    private void startVpnSafely() {
        try {
            startService(new Intent(this, KairosVpnService.class));
            MotorStateStore.setRunning(this, true);
        } catch (Exception e) {
            MotorStateStore.setRunning(this, false);
            Toast.makeText(this, "Falha ao iniciar VPN", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
