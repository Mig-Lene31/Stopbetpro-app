package com.stopbet.app;

import android.content.Context;
import java.util.Set;

public class BetDomains {

    private static Set<String> BLOCKED;

    public static void init(Context ctx) {
        if (BLOCKED == null) {
            BLOCKED = BetDomainRepository.load(ctx);
        }
    }

    public static Set<String> get() {
        return BLOCKED;
    }
}
