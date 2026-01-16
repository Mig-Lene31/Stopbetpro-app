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
        startForeground(1, ForegroundNotify.create(this));
        handler.post(loop);
    }

    private final Runnable loop = new Runnable() {
        @Override
        public void run() {

            if (!EngineRuntime.isRunning(StopHeartService.this)) {
                stopSelf();
                return;
            }

            if (TimeStore.isExpired(StopHeartService.this)) {
                EngineRuntime.block(StopHeartService.this);
            }

            if (EngineRuntime.isBlocked(StopHeartService.this)) {
                startService(new Intent(StopHeartService.this, BetBlockVpnService.class));
            } else {
                stopService(new Intent(StopHeartService.this, BetBlockVpnService.class));
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
