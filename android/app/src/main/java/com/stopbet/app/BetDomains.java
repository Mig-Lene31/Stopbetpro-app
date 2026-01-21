package com.stopbet.app;

import android.content.Context;
import java.util.HashSet;
import java.util.Set;

public class BetDomains {

    private static final Set<String> DOMAINS = new HashSet<>();

    public static void init(Context ctx) {
        if (!DOMAINS.isEmpty()) return;

        DOMAINS.add("bet365.com");
        DOMAINS.add("betfair.com");
        DOMAINS.add("sportingbet.com");
        DOMAINS.add("pixbet.com");
        DOMAINS.add("blaze.com");
        DOMAINS.add("betano.com");
        DOMAINS.add("estrelabet.com");
        DOMAINS.add("betnacional.com");
        DOMAINS.add("1xbet.com");

        DOMAINS.add("stake.com");
        DOMAINS.add("parimatch.com");
        DOMAINS.add("betway.com");
        DOMAINS.add("bodog.com");
        DOMAINS.add("rivalo.com");
        DOMAINS.add("galera.bet");
    }

    public static Set<String> get() {
        return DOMAINS;
    }
}
