package com.stopbet.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class ForegroundNotify {

    private static final String CHANNEL_ID = "kairós_protection";

    public static Notification create(Context ctx) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Proteção Kairós",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager nm = ctx.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }

        return new Notification.Builder(ctx, CHANNEL_ID)
                .setContentTitle("Proteção ativa")
                .setContentText("Monitorando apostas em tempo real")
                .setSmallIcon(android.R.drawable.ic_lock_lock)
                .setOngoing(true)
                .build();
    }
}
