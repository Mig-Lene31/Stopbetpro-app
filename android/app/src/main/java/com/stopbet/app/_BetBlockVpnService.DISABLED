package com.stopbet.app;

import android.net.VpnService;
import android.content.Intent;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

public class BetBlockVpnService extends VpnService implements Runnable {

    private static final String TAG = "KAIROS_VPN_IP";

    private Thread thread;
    private ParcelFileDescriptor tun;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (thread != null) return START_STICKY;

        Builder builder = new Builder();
        builder.setSession("KairosBlock");
        builder.addAddress("10.0.0.2", 32);
        builder.addRoute("0.0.0.0", 0);

        tun = builder.establish();

        thread = new Thread(this, "KairosIpBlockThread");
        thread.start();

        Log.d(TAG, "VPN IP BLOCK STARTED");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        try {
            if (tun != null) tun.close();
        } catch (Exception ignored) {}
        thread = null;
        Log.d(TAG, "VPN STOPPED");
        super.onDestroy();
    }

    @Override
    public void run() {
        try (
            FileInputStream in = new FileInputStream(tun.getFileDescriptor());
            FileOutputStream out = new FileOutputStream(tun.getFileDescriptor())
        ) {
            ByteBuffer packet = ByteBuffer.allocate(32767);

            while (EngineState.isBlocked(this)) {
                packet.clear();
                int len = in.read(packet.array());
                if (len <= 0) continue;

                String destIp = extractDestIp(packet.array());
                if (destIp != null && BlockedIpRanges.isBlocked(destIp)) {
                    Log.d(TAG, "IP BLOQUEADO: " + destIp);
                    continue; // DROP TOTAL
                }

                out.write(packet.array(), 0, len);
            }
        } catch (Exception e) {
            Log.e(TAG, "VPN ERROR", e);
        }
    }

    private String extractDestIp(byte[] packet) {
        try {
            int version = (packet[0] >> 4) & 0xF;
            if (version != 4) return null; // IPv4 apenas

            return (packet[16] & 0xFF) + "." +
                   (packet[17] & 0xFF) + "." +
                   (packet[18] & 0xFF) + "." +
                   (packet[19] & 0xFF);
        } catch (Exception e) {
            return null;
        }
    }
}
