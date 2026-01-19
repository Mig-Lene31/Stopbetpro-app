package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import com.stopbet.app.runtime.RuntimeHeartbeat;

public class StopHeartService extends Service {

    private final Handler handler = new Handler();

    private final Runnable loop = new Runnable() {
        @Override
        public void run() {

            if (!MotorStateStore.isRunning(StopHeartService.this)) {
                stopSelf();
                return;
            }

            double balance = AppState.getBalance(StopHeartService.this);
            RuntimeHeartbeat.tick(StopHeartService.this, balance);

            handler.postDelayed(this, 3000);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(1, ForegroundNotify.create(this, "Motor ATIVO"));
        MotorStateStore.setRunning(this, true);
        SessionStore.start(this);
        handler.post(loop);
    }

    @Override
    public void onDestroy() {
        MotorStateStore.setRunning(this, false);
        SessionStore.clear(this);
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
