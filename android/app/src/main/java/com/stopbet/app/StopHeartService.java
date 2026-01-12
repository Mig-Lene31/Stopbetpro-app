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
                handler.postDelayed(this, 5000);
                return;
            }

            if (EngineState.isBlocked(StopHeartService.this)) {
                handler.postDelayed(this, 5000);
                return;
            }

            if (TimeStore.isExpired(StopHeartService.this)) {
                EngineState.blockFor12Hours(
                        StopHeartService.this,
                        EngineState.REASON_STOP_TIME
                );
                return;
            }

            float base = DepositStore.get(StopHeartService.this);
            float current = AppState.getCurrentBalance(StopHeartService.this);

            if (LimitsStore.getWin(StopHeartService.this) > 0 &&
                current - base >= LimitsStore.getWin(StopHeartService.this)) {

                EngineState.blockFor12Hours(
                        StopHeartService.this,
                        EngineState.REASON_STOP_WIN
                );
                return;
            }

            if (LimitsStore.getLoss(StopHeartService.this) > 0 &&
                base - current >= LimitsStore.getLoss(StopHeartService.this)) {

                EngineState.blockFor12Hours(
                        StopHeartService.this,
                        EngineState.REASON_STOP_LOSS
                );
                return;
            }

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
