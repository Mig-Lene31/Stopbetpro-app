package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class RulesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("Regras e Informações Importantes");
        title.setTextSize(20);

        TextView rules = new TextView(this);
        rules.setText(
            "COMO O APP FUNCIONA\n\n" +
            "O StopBet Pro é uma ferramenta de apoio ao controle pessoal de uso.\n" +
            "Ele permite registrar valores de depósito, limites de ganho (stop win), " +
            "limites de perda (stop loss) e tempo de uso.\n\n" +

            "IMPORTANTE\n\n" +
            "- Este aplicativo NÃO garante ganhos financeiros.\n" +
            "- O app NÃO se conecta a casas de apostas.\n" +
            "- O controle depende exclusivamente do usuário.\n\n" +

            "RESPONSABILIDADE\n\n" +
            "- O uso deste aplicativo é de total responsabilidade do usuário.\n" +
            "- O desenvolvedor não se responsabiliza por perdas financeiras.\n" +
            "- O app não oferece aconselhamento financeiro ou psicológico.\n\n" +

            "SEGURANÇA\n\n" +
            "- Os dados são armazenados apenas no próprio aparelho.\n" +
            "- Nenhuma informação é enviada para servidores externos.\n\n" +

            "USO CONSCIENTE\n\n" +
            "Se você sentir que perdeu o controle, procure ajuda profissional."
        );
        rules.setTextSize(16);

        Button voltar = new Button(this);
        voltar.setText("Voltar");
        voltar.setOnClickListener(v -> finish());

        layout.addView(title);
        layout.addView(rules);
        layout.addView(voltar);

        scroll.addView(layout);
        setContentView(scroll);
    }
}
