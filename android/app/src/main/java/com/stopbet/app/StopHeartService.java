package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class StopHeartService extends Service {

    private Handler h = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();

        ForegroundNotify.ensureChannel(this);
        startForeground(1, ForegroundNotify.build(this));

        h.postDelayed(loop, 3000);
    }

    private final Runnable loop = new Runnable() {
        @Override
        public void run() {

            if (!MotorStateStore.isEnabled(StopHeartService.this)) {
                h.postDelayed(this, 5000);
                return;
            }

            if (EngineState.isBlocked(StopHeartService.this)) {
                h.postDelayed(this, 5000);
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

            float win = LimitsStore.getWin(StopHeartService.this);
            float loss = LimitsStore.getLoss(StopHeartService.this);

            if (win > 0 && (current - base) >= win) {
                EngineState.blockFor12Hours(
                        StopHeartService.this,
                        EngineState.REASON_STOP_WIN
                );
                return;
            }

            if (loss > 0 && (base - current) >= loss) {
                EngineState.blockFor12Hours(
                        StopHeartService.this,
                        EngineState.REASON_STOP_LOSS
                );
                return;
            }

            h.postDelayed(this, 5000);
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
