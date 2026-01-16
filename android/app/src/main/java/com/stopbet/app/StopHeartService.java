package com.stopbet.app;

import android.app.Service;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class StopHeartService extends Service {

    private final Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(1, ForegroundNotify.create(this, "Motor ATIVO"));
        RuntimeLog.log(this, "SERVICE STARTED");
        handler.post(loop);
    }

    private final Runnable loop = new Runnable() {
        @Override
        public void run() {

            RuntimeLog.log(StopHeartService.this, "HEARTBEAT");

            if (!EngineRuntime.isRunning(StopHeartService.this)) {
                RuntimeLog.log(StopHeartService.this, "ENGINE STOPPED");
                stopSelf();
                return;
            }

            String status = "Motor ATIVO";

            if (TimeStore.isExpired(StopHeartService.this)) {
                RuntimeLog.log(StopHeartService.this, "TIME EXPIRED → BLOCK");
                EngineRuntime.block(StopHeartService.this);
            }

            if (EngineRuntime.isBlocked(StopHeartService.this)) {
                status = "BLOQUEIO ATIVO";
                RuntimeLog.log(StopHeartService.this, "VPN START");
                startService(new Intent(StopHeartService.this, BetBlockVpnService.class));
            } else {
                stopService(new Intent(StopHeartService.this, BetBlockVpnService.class));
            }

            NotificationManager nm = getSystemService(NotificationManager.class);
            if (nm != null) {
                nm.notify(1, ForegroundNotify.create(StopHeartService.this, status));
            }

            handler.postDelayed(this, 3000);
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
