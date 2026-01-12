package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class StopHeartService extends Service {

    private final Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();

        ForegroundNotify.ensureChannel(this);
        startForeground(1, ForegroundNotify.build(this));

        handler.postDelayed(loop, 3000);
    }

    private final Runnable loop = new Runnable() {
        @Override
        public void run() {

            if (!MotorStateStore.isEnabled(StopHeartService.this)) {
                MotorStatusNotifier.update(
                        StopHeartService.this,
                        "🔴 Motor desativado",
                        "Configure o app para ativar a proteção"
                );
                handler.postDelayed(this, 5000);
                return;
            }

            if (EngineState.isBlocked(StopHeartService.this)) {
                MotorStatusNotifier.update(
                        StopHeartService.this,
                        "⛔ Acesso bloqueado",
                        "Proteção ativa por segurança"
                );
                handler.postDelayed(this, 5000);
                return;
            }

            MotorStatusNotifier.update(
                    StopHeartService.this,
                    "🟢 Proteção ativa",
                    "Monitorando apostas em tempo real"
            );

            handler.postDelayed(this, 5000);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
