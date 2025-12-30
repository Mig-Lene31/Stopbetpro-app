package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlockedActivity extends Activity {

    private TextView timer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setBackgroundColor(0xFF0D47A1);

        TextView title = new TextView(this);
        title.setText("⛔ ACESSO BLOQUEADO");
        title.setTextSize(24);
        title.setTextColor(0xFFFFFFFF);

        timer = new TextView(this);
        timer.setTextColor(0xFFFFFFFF);

        TextView info = new TextView(this);
        info.setTextColor(0xFFFFFFFF);
        info.setText(
            "O StopBet utiliza limites definidos pelo próprio usuário.\n\n" +
            "Quando um limite de ganho, perda ou tempo é atingido, o aplicativo ativa automaticamente um bloqueio temporário.\n\n" +
            "🔒 O bloqueio funciona por meio de uma VPN local, que impede o acesso APENAS a sites de apostas previamente listados.\n\n" +
            "📌 Sites bloqueados incluem, entre outros:\n" +
            "Blaze, Bet365, Betano, Pixbet, Sportsbet, Novibet, Betfair, Parimatch, Betway.\n\n" +
            "⚠️ Importante:\n" +
            "- O StopBet NÃO coleta dados pessoais.\n" +
            "- Nenhuma informação é enviada para servidores externos.\n" +
            "- O bloqueio é temporário e automático.\n" +
            "- O usuário concorda com esse funcionamento ao utilizar o aplicativo.\n\n" +
            "Este aplicativo tem finalidade de bem-estar digital e controle de comportamento."
        );

        layout.addView(title);
        layout.addView(timer);
        layout.addView(info);

        setContentView(layout);
        iniciar();
    }

    private void iniciar() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long r = EngineState.getRemainingTime(BlockedActivity.this);

                if (r <= 0) {
                    EngineState.autoUnlock(BlockedActivity.this);
                    startActivity(new Intent(BlockedActivity.this, GateActivity.class));
                    finish();
                    return;
                }

                long h = r / 3600000;
                long m = (r % 3600000) / 60000;
                long s = (r % 60000) / 1000;

                timer.setText("Tempo restante: " + h + "h " + m + "m " + s + "s");

                handler.postDelayed(this, 1000);
            }
        }, 0);
    }
}
