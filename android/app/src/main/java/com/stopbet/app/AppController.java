package com.stopbet.app;

import android.content.Context;

public class AppController {

    private static AppController instance;
    private final Context appContext;

    private AppController(Context ctx) {
        this.appContext = ctx.getApplicationContext();
    }

    public static synchronized AppController get(Context ctx) {
        if (instance == null) {
            instance = new AppController(ctx);
        }
        return instance;
    }

    // ===== INTENÇÕES DA UI =====

    public void onOpenLimits() {
        // futuramente chama motor
    }

    public void onOpenTime() {
        // futuramente chama motor
    }

    public void onRequestBlockCheck() {
        // motor entra aqui depois
    }

    public boolean isBlocked() {
        return EngineState.isBlocked(appContext);
    }
}
