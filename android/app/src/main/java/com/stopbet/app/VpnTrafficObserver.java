package com.stopbet.app;

import android.content.Context;
import android.util.Log;

public class VpnTrafficObserver {

    private static final String TAG = "KAIROS_VPN";

    public static void onHostObserved(Context ctx, String host) {
        if (!BetInterventionPolicy.shouldIntervene(ctx)) return;

        if (BetDetectionEngine.isBetDomain(host)) {
            Log.w(TAG, "INTERVENÇÃO AUTORIZADA (domínio): " + host);
        }
    }
}
