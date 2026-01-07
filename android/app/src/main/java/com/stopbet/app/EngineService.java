package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class EngineService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!EngineGuard.canUseMotor(this)) {
            stopSelf();
            return START_NOT_STICKY;
        }

        if (!EngineCooldownStore.canRun(this)) {
            stopSelf();
            return START_NOT_STICKY;
        }

        float saldo = intent != null
            ? intent.getFloatExtra("saldo", -1f)
            : -1f;

        if (saldo >= 0) {

            boolean stable = BalanceStabilityEngine.push(this, saldo);

            if (stable && !BalanceConfirmState.isConfirmed(this)) {
                startService(new Intent(this, BalanceConfirmOverlayService.class));
                stopSelf();
                return START_NOT_STICKY;
            }

            if (stable && BalanceConfirmState.isConfirmed(this)) {
                EngineExecutor.process(this, saldo);
                EngineCooldownStore.markRun(this);
                BalanceConfirmState.clear(this);
                BalanceStabilityEngine.clear(this);
            }
        }

        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
