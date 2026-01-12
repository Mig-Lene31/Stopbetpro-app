package com.stopbet.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BalanceConfirmOverlayService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        float saldo = intent.getFloatExtra("saldo", -1f);
        if (saldo < 0) stopSelf();

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(40,40,40,40);
        box.setBackgroundColor(0xFF1B263B);

        TextView txt = new TextView(this);
        txt.setText("Saldo detectado: R$ " + saldo + "\nConfirma?");
        txt.setTextColor(0xFFFFFFFF);

        Button yes = new Button(this);
        yes.setText("SIM");
        yes.setOnClickListener(v -> {
            BalanceConfirmState.confirm(this);
            wm.removeView(box);
            stopSelf();
        });

        Button no = new Button(this);
        no.setText("NÃO");
        no.setOnClickListener(v -> {
            BalanceConfirmState.clear(this);
            wm.removeView(box);
            stopSelf();
        });

        box.addView(txt);
        box.addView(yes);
        box.addView(no);

        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                -3
        );
        p.gravity = Gravity.CENTER;

        wm.addView(box, p);

        return START_NOT_STICKY;
    }

    @Override public IBinder onBind(Intent intent) { return null; }
}
