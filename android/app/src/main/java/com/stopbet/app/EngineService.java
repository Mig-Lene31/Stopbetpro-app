package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class EngineService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!MotorStateStore.isEnabled(this)) return START_NOT_STICKY;
        if (EngineState.isBlocked(this)) return START_NOT_STICKY;

        float saldo = intent != null ? intent.getFloatExtra("saldo", -1f) : -1f;
        if (saldo < 0) return START_NOT_STICKY;

        if (!BalanceConfirmState.isConfirmed(this)) {
            Intent i = new Intent(this, BalanceConfirmOverlayService.class);
            i.putExtra("saldo", saldo);
            startService(i);
            return START_NOT_STICKY;
        }

        EngineExecutor.process(this, saldo);
        BalanceConfirmState.clear(this);

        return START_NOT_STICKY;
    }

    @Override public IBinder onBind(Intent intent) { return null; }
}
