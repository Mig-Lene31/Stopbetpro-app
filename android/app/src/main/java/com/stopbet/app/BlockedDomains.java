package com.stopbet.app;

import java.util.HashSet;
import java.util.Set;

public class BlockedDomains {

    public static Set<String> list() {
        Set<String> domains = new HashSet<>();

        domains.add("bet365.com");
        domains.add("betfair.com");
        domains.add("sportingbet.com");
        domains.add("pixbet.com");
        domains.add("blaze.com");
        domains.add("betano.com");
        domains.add("estrelabet.com");
        domains.add("betnacional.com");
        domains.add("1xbet.com");

        domains.add("stake.com");
        domains.add("parimatch.com");
        domains.add("betway.com");
        domains.add("bodog.com");
        domains.add("rivalo.com");
        domains.add("galera.bet");

        return domains;
    }
}
