package com.stopbet.app;

import android.util.Log;

public class VpnTrafficObserver {

    private static final String TAG = "KAIROS_VPN";

    public static void onHostObserved(String host) {
        if (BetDetectionEngine.isBetDomain(host)) {
            Log.w(TAG, "Domínio de aposta detectado: " + host);
        }
    }
}
