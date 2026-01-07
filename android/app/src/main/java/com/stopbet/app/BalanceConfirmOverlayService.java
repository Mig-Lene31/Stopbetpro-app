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

        Button yes = new Button(this);
        yes.setText("SIM");

        Button no = new Button(this);
        no.setText("NÃO");

        yes.setOnClickListener(v -> {
            BalanceConfirmationStore.confirm(this);
            FinanceEngineState.clear(this);
            stopSelf();
        });

        no.setOnClickListener(v -> {
            int tries = BalanceConfirmationStore.incTry(this);
            FinanceEngineState.setPending(this, false);

            if (tries >= 3) {
                MotorState.forceDisable(this);
                TimeStore.clear(this);
                TimeStore.setMinutes(this, 1);
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
