package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UnlockActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("🔓 Desbloqueio de Acesso");
        title.setTextSize(20);

        TextView info = new TextView(this);
        info.setText(
            "Para remover o bloqueio antes do tempo automático:\n\n" +
            "💰 Valor: R$ 50,00\n\n" +
            "📌 Pix:\n" +
            "SUA_CHAVE_PIX_AQUI\n\n" +
            "📲 Após o pagamento, envie o comprovante pelo WhatsApp informando seu ID:\n\n" +
            UserIdentity.getId(this)
        );
        info.setTextSize(16);

        Button whatsapp = new Button(this);
        whatsapp.setText("📤 Enviar comprovante no WhatsApp");

        whatsapp.setOnClickListener(v -> {
            String url = "https://wa.me/55SEU_NUMERO_AQUI"
                    + "?text=Olá,%20segue%20comprovante%20do%20desbloqueio.%0AID:%20"
                    + UserIdentity.getId(this);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });

        layout.addView(title);
        layout.addView(info);
        layout.addView(whatsapp);

        setContentView(layout);
    }
}
