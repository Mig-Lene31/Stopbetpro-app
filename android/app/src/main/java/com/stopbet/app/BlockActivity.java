package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BlockActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);

        Button unlockButton = findViewById(R.id.btnUnlock);
        TextView infoText = findViewById(R.id.txtInfo);

        BlockRulesManager rules =
                BlockRulesManager.getInstance(this);

        infoText.setText("Bloqueio ativo por 12h.\nDesbloqueio antecipado: R$ 50,00");

        unlockButton.setOnClickListener(v -> {
            // 💰 Pagamento real entra depois
            rules.forceUnlock();
            finish();
        });
    }
}
