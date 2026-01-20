package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class StopHeartService extends Service {

    private static final String TAG = "KAIROS_HEART";

    private final Handler handler = new Handler();

    private final Runnable loop = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "HEARTBEAT ATIVO");
            handler.postDelayed(this, 3000);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        MotorStateStore.setRunning(this, true);
        Log.d(TAG, "SERVICE STARTED");
        handler.post(loop);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "SERVICE STOPPED");
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
