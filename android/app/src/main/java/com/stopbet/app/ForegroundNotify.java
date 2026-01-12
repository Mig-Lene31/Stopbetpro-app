package com.stopbet.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class ForegroundNotify {

    public static final String CHANNEL_ID = "kairios_engine";

    public static void ensureChannel(Context ctx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Kairós Proteção",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel.setDescription("Proteção ativa em segundo plano");

            NotificationManager nm = ctx.getSystemService(NotificationManager.class);
            if (nm != null) {
                nm.createNotificationChannel(channel);
            }
        }
    }

    public static Notification build(Context ctx) {
        Notification.Builder b;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            b = new Notification.Builder(ctx, CHANNEL_ID);
        } else {
            b = new Notification.Builder(ctx);
        }

        b.setContentTitle("Kairós ativo")
         .setContentText("Protegendo você em segundo plano")
         .setSmallIcon(android.R.drawable.ic_lock_lock)
         .setOngoing(true);

        return b.build();
    }
}
