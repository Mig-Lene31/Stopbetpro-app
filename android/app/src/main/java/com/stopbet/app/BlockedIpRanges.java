package com.stopbet.app;

import java.util.Arrays;
import java.util.List;

public class BlockedIpRanges {

    // Ranges usados por sites de apostas (Cloudflare + infra comum)
    public static List<String> ranges() {
        return Arrays.asList(
            "104.16.",   // Cloudflare
            "104.17.",
            "172.64.",
            "172.65.",
            "188.114.",  // Cloudflare edge
            "162.158."   // Cloudflare
        );
    }

    public static boolean isBlocked(String ip) {
        for (String prefix : ranges()) {
            if (ip.startsWith(prefix)) return true;
        }
        return false;
    }
}
