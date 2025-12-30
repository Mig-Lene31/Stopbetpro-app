package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Primeira execução: mostra informações
        if (!InfoAcceptedStore.isAccepted(this)) {
            startActivity(new Intent(this, InfoActivity.class));
        } else {
            startActivity(new Intent(this, LockScreenActivity.class));
        }

        finish();
    }
}
