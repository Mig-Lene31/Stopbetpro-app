package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class EngineService extends Service {

    private Handler handler = new Handler();
    private long startTime;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTime = System.currentTimeMillis();
        handler.post(checkRunnable);
        return START_STICKY;
    }

    private Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {

            if (!MotorState.isEnabled(EngineService.this)) {
                stopSelf();
                return;
            }

            int maxMinutes = TimeStore.getMinutes(EngineService.this);
            if (maxMinutes > 0) {
                long used = (System.currentTimeMillis() - startTime) / 60000;
                if (used >= maxMinutes) {
                    MotorState.forceDisable(EngineService.this);
                    EngineState.blockFor12Hours(
                        EngineService.this,
                        EngineState.REASON_STOP_TIME
                    );
                    stopSelf();
                    return;
                }
            }

            handler.postDelayed(this, 60 * 1000);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
