package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Se estiver bloqueado, vai direto pra tela azul
        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
            return;
        }

        // Fluxo NORMAL antigo (mantém aparência original)
        startActivity(new Intent(this, PaymentActivity.class));
        finish();
    }
}
