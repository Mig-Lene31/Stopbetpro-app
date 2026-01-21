package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.widget.Toast;

public class VpnPermissionActivity extends Activity {

    private static final int REQ_VPN = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = VpnService.prepare(this);
        if (intent != null) {
            startActivityForResult(intent, REQ_VPN);
        } else {
            onActivityResult(REQ_VPN, RESULT_OK, null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_VPN && resultCode == RESULT_OK) {

            // 🔥 AQUI É O CORAÇÃO DO SISTEMA 🔥
            MotorStateStore.setRunning(this, true);
            EngineState.blockFor12Hours(this);

            startService(new Intent(this, BetBlockVpnService.class));

            Toast.makeText(this, "Proteção ATIVADA", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
