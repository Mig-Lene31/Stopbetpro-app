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

    private WindowManager wm;
    private LinearLayout view;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        view = new LinearLayout(this);
        view.setOrientation(LinearLayout.VERTICAL);
        view.setPadding(40,40,40,40);
        view.setBackgroundColor(0xEE000000);

        TextView txt = new TextView(this);
        txt.setText("Esse valor é o seu saldo real?");
        txt.setTextColor(0xFFFFFFFF);
        txt.setGravity(Gravity.CENTER);

        Button yes = new Button(this);
        yes.setText("SIM");

        Button no = new Button(this);
        no.setText("NÃO");

        yes.setOnClickListener(v -> {
            BalanceConfirmState.confirm(this);
            stopSelf();
        });

        no.setOnClickListener(v -> {
            int tries = BalanceConfirmState.incTry(this);

            if (tries >= 3) {
                MotorState.forceDisable(this);
                TimeStore.setMinutes(this, 1);
            }

            stopSelf();
        });

        view.addView(txt);
        view.addView(yes);
        view.addView(no);

        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        );

        p.gravity = Gravity.TOP;
        wm.addView(view, p);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (view != null) wm.removeView(view);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
