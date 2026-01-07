package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EngineStatusOverlayService extends Service {

    private WindowManager wm;
    private LinearLayout view;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        view = new LinearLayout(this);
        view.setPadding(40,40,40,40);
        view.setBackgroundColor(0xCC000000);

        TextView txt = new TextView(this);
        txt.setTextColor(0xFFFFFFFF);
        txt.setGravity(Gravity.CENTER);

        if (EngineState.isBlocked(this)) {
            txt.setText("⏳ Kairós bloqueado temporariamente");
        } else if (!MotorState.isEnabled(this)) {
            txt.setText("⛔ Proteção desativada");
        } else {
            txt.setText("🟢 Kairós ativo e protegido");
        }

        view.addView(txt);

        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        );

        p.gravity = Gravity.TOP;
        wm.addView(view, p);

        view.postDelayed(this::stopSelf, 3000);

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
