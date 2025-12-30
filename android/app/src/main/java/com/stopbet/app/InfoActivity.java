package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("ℹ️ Sobre o StopBet Pro");
        title.setTextSize(22);

        TextView body = new TextView(this);
        body.setTextSize(16);
        body.setText(
            "O StopBet Pro é um aplicativo de controle de comportamento e bem-estar digital.\n\n" +

            "Ele foi criado para ajudar pessoas que desejam reduzir ou interromper o acesso a sites de apostas, " +
            "utilizando limites definidos voluntariamente pelo próprio usuário.\n\n" +

            "━━━━━━━━━━━━━━━━━━━━━━\n\n" +

            "🎯 LIMITES CONFIGURÁVEIS\n\n" +
            "O usuário pode definir limites de:\n" +
            "- ganho\n" +
            "- perda\n" +
            "- tempo de uso\n\n" +
            "Quando qualquer um desses limites é atingido, o aplicativo ativa automaticamente um bloqueio temporário.\n\n" +

            "━━━━━━━━━━━━━━━━━━━━━━\n\n" +

            "🔒 COMO FUNCIONA O BLOQUEIO\n\n" +
            "O bloqueio é realizado por meio de uma VPN local, recurso oficial do sistema Android.\n\n" +
            "Essa VPN funciona apenas no próprio dispositivo e:\n" +
            "- não coleta dados pessoais\n" +
            "- não envia informações para servidores externos\n" +
            "- não redireciona tráfego de internet\n\n" +
            "Durante o bloqueio, o acesso é interrompido apenas para sites de apostas previamente listados.\n" +
            "Outros aplicativos e sites continuam funcionando normalmente.\n\n" +

            "━━━━━━━━━━━━━━━━━━━━━━\n\n" +

            "⛔ BLOQUEIO TEMPORÁRIO\n\n" +
            "O bloqueio possui duração definida pelo sistema do aplicativo.\n" +
            "Após o término do período, o acesso é restabelecido automaticamente.\n\n" +
            "Em situações específicas, o desbloqueio pode ser realizado por meio de acesso administrativo.\n\n" +

            "━━━━━━━━━━━━━━━━━━━━━━\n\n" +

            "⚠️ AVISOS IMPORTANTES\n\n" +
            "- O StopBet Pro não garante bloqueio absoluto contra todos os métodos possíveis de acesso.\n" +
            "- Usuários avançados podem, se desejarem, alterar permissões do sistema.\n" +
            "- O aplicativo não substitui acompanhamento psicológico, médico ou financeiro.\n\n" +

            "━━━━━━━━━━━━━━━━━━━━━━\n\n" +

            "📌 FINALIDADE\n\n" +
            "O objetivo do StopBet Pro é reduzir impulsividade, aumentar consciência de uso " +
            "e ajudar o usuário a manter limites que ele mesmo escolheu."
        );

        layout.addView(title);
        layout.addView(body);
        scroll.addView(layout);

        setContentView(scroll);
    }
}
