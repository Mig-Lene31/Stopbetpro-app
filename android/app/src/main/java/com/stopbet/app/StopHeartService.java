package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class StopHeartService extends Service {

    private Handler handler;
    private Runnable loop;

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler();

        loop = new Runnable() {
            @Override
            public void run() {

                // Motor desligado = não vigia
                if (!EngineToggleStore.isEnabled(StopHeartService.this)) {
                    handler.postDelayed(this, 5000);
                    return;
                }

                // Já bloqueado? não repete
                if (EngineState.isBlocked(StopHeartService.this)) {
                    handler.postDelayed(this, 5000);
                    return;
                }

                float win = LimitsStore.getWin(StopHeartService.this);
                float loss = LimitsStore.getLoss(StopHeartService.this);
                float balance = AppState.getCurrentBalance(StopHeartService.this);

                // Stop Win
                if (win > 0 && balance >= win) {
                    EngineState.blockFor12Hours(StopHeartService.this);
                    return;
                }

                // Stop Loss
                if (loss > 0 && balance <= loss) {
                    EngineState.blockFor12Hours(StopHeartService.this);
                    return;
                }

                // Tempo diário
                int limitMinutes = TimeStore.getMinutes(StopHeartService.this);
                int usedMinutes = TimeStore.getUsedMinutesToday(StopHeartService.this);

                if (limitMinutes > 0 && usedMinutes >= limitMinutes) {
                    EngineState.blockFor12Hours(StopHeartService.this);
                    return;
                }

                handler.postDelayed(this, 5000);
            }
        };

        handler.post(loop);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(loop);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
