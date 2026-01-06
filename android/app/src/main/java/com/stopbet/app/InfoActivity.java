package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);
        TextView text = new TextView(this);
        text.setText(
            "Aviso Importante\n\n" +
            "Este aplicativo atua como um sistema de proteção.\n\n" +
            "Você deve configurar corretamente seus limites.\n\n" +
            "Ao continuar, você concorda com os termos."
        );

        Button accept = new Button(this);
        accept.setText("Li e aceito");

        accept.setOnClickListener(v -> {
            InfoAcceptedStore.accept(this);
            startActivity(new Intent(this, GateActivity.class));
            finish();
        });

        scroll.addView(text);
        setContentView(scroll);
        addContentView(accept,
            new android.widget.FrameLayout.LayoutParams(
                android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
                android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
            )
        );
    }
}
