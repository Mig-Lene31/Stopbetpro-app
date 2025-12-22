package com.stopbet.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

public class SafeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        } catch (Throwable t) {
            showError(t);
        }
    }

    protected void safeSetContentView(int layoutId) {
        try {
            setContentView(layoutId);
        } catch (Throwable t) {
            showError(t);
        }
    }

    protected void showError(Throwable t) {
        new AlertDialog.Builder(this)
                .setTitle("ERRO CRÍTICO")
                .setMessage(t.getClass().getSimpleName() + "\n\n" + t.getMessage())
                .setCancelable(false)
                .setPositiveButton("Fechar", (d, w) -> finish())
                .show();
    }
}
