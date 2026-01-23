package com.stopbet.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.ParcelFileDescriptor;

public class KairosVpnService extends VpnService {

    private static final String CHANNEL_ID = "KAIROS_VPN_CHANNEL";
    private ParcelFileDescriptor vpnInterface;
    private Thread keepAlive;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startForeground(1, buildNotification());

        try {
            Builder builder = new Builder();
            builder.setSession("Kairós Proteção Ativa");
            builder.addAddress("10.0.0.2", 32);
            builder.addDnsServer("8.8.8.8");
            builder.addRoute("0.0.0.0", 0);

            vpnInterface = builder.establish();

            if (vpnInterface == null) {
                MotorStateStore.setRunning(this, false);
                stopSelf();
                return START_NOT_STICKY;
            }

            MotorStateStore.setRunning(this, true);
            AccessibilityFlowStore.clear(this);

            keepAlive = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.sleep(10000);
                    }
                } catch (InterruptedException ignored) {}
            });
            keepAlive.start();

            return START_STICKY;

        } catch (Exception e) {
            MotorStateStore.setRunning(this, false);
            stopSelf();
            return START_NOT_STICKY;
        }
    }

    @Override
    public void onDestroy() {
        try {
            if (vpnInterface != null) vpnInterface.close();
        } catch (Exception ignored) {}

        if (keepAlive != null) keepAlive.interrupt();

        MotorStateStore.setRunning(this, false);
        super.onDestroy();
    }

    private Notification buildNotification() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Kairós VPN",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager nm = getSystemService(NotificationManager.class);
            if (nm != null) nm.createNotificationChannel(channel);
        }

        return new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Kairós ativo")
                .setContentText("Proteção contra apostas em funcionamento")
                .setSmallIcon(android.R.drawable.ic_lock_lock)
                .build();
    }
}
