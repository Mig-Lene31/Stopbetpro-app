package com.stopbet.app;

import android.net.VpnService;
import android.content.Intent;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class BetBlockVpnService extends VpnService implements Runnable {

    private static final String TAG = "KAIROS_VPN";

    private Thread thread;
    private ParcelFileDescriptor tun;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (thread != null) return START_STICKY;

        Log.d(TAG, "VPN STARTED");

        BetDomains.init(this);

        Builder builder = new Builder();
        builder.setSession("KairosVPN");
        builder.addAddress("10.0.0.2", 32);
        builder.addDnsServer("8.8.8.8");
        builder.addRoute("0.0.0.0", 0);

        tun = builder.establish();

        thread = new Thread(this, "KairosVpnThread");
        thread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "VPN STOPPED");
        try {
            if (tun != null) tun.close();
        } catch (Exception ignored) {}
        thread = null;
        super.onDestroy();
    }

    @Override
    public void run() {
        try (FileInputStream in = new FileInputStream(tun.getFileDescriptor())) {
            ByteBuffer packet = ByteBuffer.allocate(32767);

            while (EngineState.isBlocked(this)) {
                packet.clear();
                int len = in.read(packet.array());
                if (len <= 0) continue;

                String payload = new String(
                        packet.array(),
                        0,
                        len,
                        StandardCharsets.ISO_8859_1
                );

                for (String d : BetDomains.get()) {
                    if (payload.contains(d)) {
                        Log.d(TAG, "BLOQUEADO: " + d);
                        packet.clear();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "VPN ERROR", e);
        }
    }
}
