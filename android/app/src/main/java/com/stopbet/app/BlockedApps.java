package com.stopbet.app;

import java.util.HashSet;
import java.util.Set;

public class BlockedApps {

    public static Set<String> list() {
        Set<String> apps = new HashSet<>();

        // Apps de apostas conhecidos
        apps.add("com.blaze.android");
        apps.add("com.bet365");
        apps.add("com.betano");
        apps.add("com.pixbet");
        apps.add("com.sportingbet");
        apps.add("com.betfair");
        apps.add("com.stake");

        // Navegadores
        apps.add("com.android.chrome");
        apps.add("org.mozilla.firefox");
        apps.add("com.opera.browser");
        apps.add("com.microsoft.emmx");

        return apps;
    }
}
