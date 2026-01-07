package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BalanceConfirmOverlayService extends Service {

    private WindowManager windowManager;
    private LinearLayout overlay;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        overlay = new LinearLayout(this);
        overlay.setOrientation(LinearLayout.VERTICAL);
        overlay.setPadding(40,40,40,40);
        overlay.setBackgroundColor(0xEE0A2A66);

        TextView text = new TextView(this);
        text.setText("Esse valor é o seu saldo real?");
        text.setTextColor(0xFFFFFFFF);
        text.setGravity(Gravity.CENTER);

        Button yes = new Button(this);
        yes.setText("SIM");

        Button no = new Button(this);
        no.setText("NÃO");

        yes.setOnClickListener(v -> {
            BalanceConfirmationStore.confirm(this);
            stopSelf();
        });

        no.setOnClickListener(v -> {
            int tries = BalanceConfirmationStore.incTry(this);
            if (tries >= 3) {
                MotorState.forceDisable(this);
            }
            stopSelf();
        });

        overlay.addView(text);
        overlay.addView(yes);
        overlay.addView(no);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP;

        windowManager.addView(overlay, params);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlay != null) windowManager.removeView(overlay);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
