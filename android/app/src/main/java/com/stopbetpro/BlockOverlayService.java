package com.stopbetpro;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.graphics.Color;

public class BlockOverlayService extends Service {

    private WindowManager windowManager;
    private TextView overlayView;

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String message = intent != null
                ? intent.getStringExtra("message")
                : "BLOQUEADO";

        if (overlayView != null) {
            overlayView.setText(message);
            return START_STICKY;
        }

        overlayView = new TextView(this);
        overlayView.setText(message);
        overlayView.setTextColor(Color.WHITE);
        overlayView.setTextSize(20);
        overlayView.setGravity(Gravity.CENTER);
        overlayView.setBackgroundColor(0xDD000000);

        WindowManager.LayoutParams params =
            new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
            );

        windowManager.addView(overlayView, params);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null) {
            windowManager.removeView(overlayView);
            overlayView = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
