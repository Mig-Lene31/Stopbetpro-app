package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        TextView title = new TextView(this);
        title.setText("Informações e Uso");
        title.setTextSize(20);

        TextView text = new TextView(this);
        text.setText(
            "Este aplicativo foi criado como ferramenta de controle.\n\n" +

            "O que o app FAZ:\n" +
            "- Bloqueia o acesso quando limites são atingidos\n" +
            "- Permite configurar Stop Win e Stop Loss\n" +
            "- Limita o tempo diário de uso\n\n" +

            "O que o app NÃO FAZ:\n" +
            "- Não garante ganhos\n" +
            "- Não indica apostas\n" +
            "- Não substitui decisões pessoais\n\n" +

            "Sobre bloqueios:\n" +
            "- Bloqueios automáticos ocorrem ao atingir limites\n" +
            "- Durante bloqueio, o motor não pode ser ativado\n\n" +

            "Administração:\n" +
            "- O desbloqueio só ocorre via administrador\n" +
            "- Liberações seguem regras internas\n\n" +

            "Ao usar este app, você concorda com estas regras."
        );
        text.setTextSize(16);

        layout.addView(title);
        layout.addView(text);

        scroll.addView(layout);
        setContentView(scroll);
    }
}
