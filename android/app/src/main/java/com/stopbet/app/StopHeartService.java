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
        startForeground(1, ForegroundNotify.create(this, "Motor ATIVO"));
        MotorStateStore.setRunning(this, true);
        SessionStore.start(this);
        handler.post(loop);
    }

    private final Runnable loop = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 3000);
        }
    };

    @Override
    public void onDestroy() {
        MotorStateStore.setRunning(this, false);
        SessionStore.clear(this);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
