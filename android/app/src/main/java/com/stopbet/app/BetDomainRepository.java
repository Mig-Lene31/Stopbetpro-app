package com.stopbet.app;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BetDomainRepository {

    public static Set<String> load(Context ctx) {
        Set<String> domains = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(
                new InputStreamReader(ctx.getAssets().open("blocked_domains.txt"))
            );
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim().toLowerCase();
                if (line.isEmpty() || line.startsWith("#")) continue;
                domains.add(line);
            }
            br.close();
        } catch (Exception ignored) {}
        return domains;
    }
}
