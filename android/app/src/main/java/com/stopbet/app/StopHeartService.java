package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class StopHeartService extends Service {

    private final Handler handler = new Handler();

    private final Runnable loop = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 3000);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        MotorStateStore.setRunning(this, true);
        handler.post(loop);
    }

    @Override
    public void onDestroy() {
        MotorStateStore.setRunning(this, false);
        handler.removeCallbacks(loop);
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
