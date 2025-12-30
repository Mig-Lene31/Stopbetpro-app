package com.stopbet.app;

import android.net.VpnService;
import android.content.Intent;
import android.os.ParcelFileDescriptor;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class BetBlockVpnService extends VpnService implements Runnable {

    private Thread thread;
    private ParcelFileDescriptor tun;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (thread != null) return START_STICKY;

        Builder builder = new Builder();
        builder.setSession("StopBet VPN");
        builder.addAddress("10.0.0.2", 32);
        builder.addDnsServer("8.8.8.8");
        builder.addRoute("0.0.0.0", 0);

        tun = builder.establish();

        thread = new Thread(this, "StopBetVpnThread");
        thread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
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

                String payload = new String(packet.array(), 0, len, StandardCharsets.ISO_8859_1);

                if (isDnsQuery(payload) && containsBetDomain(payload)) {
                    // Bloqueia SOMENTE sites de aposta
                    continue;
                }

                // tráfego normal segue (não bloqueado)
            }
        } catch (Exception ignored) {}
    }

    private boolean isDnsQuery(String data) {
        return data.contains("\u0000\u0001\u0000\u0001"); // padrão simples DNS query
    }

    private boolean containsBetDomain(String data) {
        for (String domain : BetDomains.BLOCKED) {
            if (data.toLowerCase().contains(domain)) {
                return true;
            }
        }
        return false;
    }
}
