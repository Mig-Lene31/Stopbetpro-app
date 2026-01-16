package com.stopbet.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class ForegroundNotify {

    private static final String CHANNEL_ID = "kairos_protection";

    public static Notification create(Context ctx, String status) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Proteção Kairos",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager nm = ctx.getSystemService(NotificationManager.class);
            if (nm != null) {
                nm.createNotificationChannel(channel);
            }
        }

        return new Notification.Builder(ctx, CHANNEL_ID)
                .setContentTitle("Kairós em execução")
                .setContentText(status)
                .setSmallIcon(android.R.drawable.ic_lock_lock)
                .setOngoing(true)
                .build();
    }
}
