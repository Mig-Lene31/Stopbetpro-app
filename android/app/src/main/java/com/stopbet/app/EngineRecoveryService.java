package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class EngineRecoveryService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (EngineRecoveryStore.canRecover(this)) {
            EngineState.clearBlock(this);
            MotorState.enable(this);
            BalanceConfirmState.clear(this);
            BalanceStabilityEngine.clear(this);
            EngineCooldownStore.clear(this);
            TimeStore.clear(this);
            EngineRecoveryStore.clear(this);
        }

        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
