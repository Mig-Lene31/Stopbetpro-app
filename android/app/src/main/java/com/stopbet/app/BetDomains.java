package com.stopbet.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BetDomains {

    public static final Set<String> BLOCKED = new HashSet<>(Arrays.asList(
        "blaze.com",
        "blaze.bet",
        "777.com",
        "sportbet.com",
        "sportbets.com",
        "sportsbet.io",
        "sportsdasorte.com",
        "bet365.com",
        "betano.com",
        "pixbet.com",
        "novibet.com",
        "estrelabet.com",
        "betfair.com",
        "parimatch.com",
        "betway.com"
    ));

    public static boolean isBlocked(String host) {
        if (host == null) return false;
        host = host.toLowerCase();
        for (String d : BLOCKED) {
            if (host.equals(d) || host.endsWith("." + d)) return true;
        }
        return false;
    }
}
