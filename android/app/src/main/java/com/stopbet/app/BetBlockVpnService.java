package com.stopbet.app;

import android.net.VpnService;
import android.content.Intent;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class BetBlockVpnService extends VpnService implements Runnable {

    private static final String TAG = "KAIROS_DNS";

    private Thread thread;
    private ParcelFileDescriptor tun;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (thread != null) return START_STICKY;

        BetDomains.init(this);

        Builder builder = new Builder();
        builder.setSession("KairosDNS");
        builder.addAddress("10.0.0.2", 32);
        builder.addDnsServer("8.8.8.8");
        builder.addRoute("0.0.0.0", 0);

        tun = builder.establish();

        thread = new Thread(this, "KairosDnsThread");
        thread.start();

        Log.d(TAG, "VPN DNS STARTED");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        try {
            if (tun != null) tun.close();
        } catch (Exception ignored) {}
        thread = null;
        Log.d(TAG, "VPN DNS STOPPED");
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

                if (isDnsQuery(packet.array(), len)) {
                    String domain = extractDomain(packet.array(), len);
                    if (domain != null && isBlockedDomain(domain)) {
                        Log.d(TAG, "DNS BLOQUEADO: " + domain);
                        continue; // descarta o pacote DNS
                    }
                }

                out.write(packet.array(), 0, len);
            }
        } catch (Exception e) {
            Log.e(TAG, "VPN ERROR", e);
        }
    }

    private boolean isDnsQuery(byte[] data, int len) {
        if (len < 28) return false;
        int protocol = data[9] & 0xFF;
        int destPort = ((data[22] & 0xFF) << 8) | (data[23] & 0xFF);
        return protocol == 17 && destPort == 53; // UDP + DNS
    }

    private String extractDomain(byte[] data, int len) {
        try {
            int i = 28;
            StringBuilder domain = new StringBuilder();
            while (i < len) {
                int partLen = data[i++] & 0xFF;
                if (partLen == 0) break;
                if (i + partLen > len) return null;
                if (domain.length() > 0) domain.append(".");
                domain.append(new String(data, i, partLen, StandardCharsets.UTF_8));
                i += partLen;
            }
            return domain.toString().toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isBlockedDomain(String domain) {
        for (String d : BetDomains.get()) {
            if (domain.endsWith(d)) return true;
        }
        return false;
    }
}
