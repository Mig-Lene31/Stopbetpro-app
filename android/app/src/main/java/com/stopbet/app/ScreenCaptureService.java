package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ScreenCaptureService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Fase 5.1: apenas serviço placeholder
        // Captura real e OCR entram depois
        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
