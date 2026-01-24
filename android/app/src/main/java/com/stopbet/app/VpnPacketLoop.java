package com.stopbet.app;

import android.os.ParcelFileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

public class VpnPacketLoop implements Runnable {

    private final ParcelFileDescriptor vpnInterface;

    public VpnPacketLoop(ParcelFileDescriptor vpnInterface) {
        this.vpnInterface = vpnInterface;
    }

    @Override
    public void run() {
        FileInputStream in = new FileInputStream(vpnInterface.getFileDescriptor());
        byte[] buffer = new byte[32767];

        while (true) {
            try {
                int length = in.read(buffer);
                if (length > 0) {
                    String host = DnsPacketParser.extractHostname(buffer);
                    if (host != null) {
                        VpnTrafficObserver.onHostObserved(host);
                    }
                }
            } catch (IOException e) {
                break;
            }
        }
    }
}
