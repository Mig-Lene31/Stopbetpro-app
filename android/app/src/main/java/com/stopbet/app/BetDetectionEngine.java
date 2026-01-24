package com.stopbet.app;

import java.util.Locale;

public class BetDetectionEngine {

    public static boolean isBetDomain(String host) {
        if (host == null) return false;
        String h = host.toLowerCase(Locale.US);

        for (String domain : BetDomains.list()) {
            if (h.contains(domain)) return true;
        }
        return false;
    }

    public static boolean isBetPackage(String packageName) {
        if (packageName == null) return false;
        String p = packageName.toLowerCase(Locale.US);

        for (String site : BetSites.KEYWORDS) {
            if (p.contains(site)) return true;
        }
        return false;
    }
}
