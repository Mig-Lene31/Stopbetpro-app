package com.stopbet.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.ParcelFileDescriptor;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class KairosVpnService extends VpnService {

    private static final String CHANNEL_ID = "KAIROS_VPN_CHANNEL";

    private ParcelFileDescriptor vpnInterface;
    private Thread vpnThread;
    private boolean running = false;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startForeground(1, buildNotification());

        if (running) return START_STICKY;

        try {
            Builder builder = new Builder();
            builder.setSession("Kairós Proteção Ativa");
            builder.addAddress("10.0.0.2", 32);
            builder.addDnsServer("8.8.8.8");
            builder.addRoute("0.0.0.0", 0);

            vpnInterface = builder.establish();
            if (vpnInterface == null) {
                stopSelf();
                return START_NOT_STICKY;
            }

            running = true;
            MotorStateStore.setRunning(this, true);

            FileInputStream in = new FileInputStream(vpnInterface.getFileDescriptor());
            FileOutputStream out = new FileOutputStream(vpnInterface.getFileDescriptor());

            vpnThread = new Thread(() -> {
                byte[] buffer = new byte[32767];
                try {
                    while (running) {
                        int len = in.read(buffer);
                        if (len > 0) {
                            out.write(buffer, 0, len);
                        }
                    }
                } catch (Exception ignored) {}
            });
            vpnThread.start();

            return START_STICKY;

        } catch (Exception e) {
            stopSelf();
            return START_NOT_STICKY;
        }
    }

    @Override
    public void onDestroy() {
        running = false;
        MotorStateStore.setRunning(this, false);

        try {
            if (vpnThread != null) vpnThread.interrupt();
            if (vpnInterface != null) vpnInterface.close();
        } catch (Exception ignored) {}

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
                .setContentText("VPN ativa (modo proteção)")
                .setSmallIcon(android.R.drawable.ic_lock_lock)
                .build();
    }
}
