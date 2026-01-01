package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1️⃣ Não aceitou termos
        if (!InfoAcceptedStore.hasAccepted(this)) {
            startActivity(new Intent(this, InfoActivity.class));
        }
        // 2️⃣ Aceitou termos, mas NÃO está autorizado
        else if (!AuthState.isUnlocked(this)) {
            startActivity(new Intent(this, AuthLockActivity.class));
        }
        // 3️⃣ Autorizado (30 dias)
        else {
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }
}
