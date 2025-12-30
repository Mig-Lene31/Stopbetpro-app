package com.stopbet.app;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnchorRules {

    private static final String[] PRIMARY = {
        "saldo", "saldo disponível", "saldo atual",
        "wallet", "balance", "available balance",
        "funds", "my balance", "r$"
    };

    private static final String[] SECONDARY = {
        "disponível", "total", "conta", "crédito", "valor disponível"
    };

    private static final String[] FORBIDDEN = {
        "bônus", "bonus", "free bet", "freebet",
        "ganhos recentes", "últimos ganhadores",
        "aposta", "stake", "odds",
        "prêmio potencial", "possível ganho"
    };

    private static final Pattern MONEY =
            Pattern.compile("(r\\$\\s*)?(\\d{1,3}(?:\\.\\d{3})*(?:,\\d{2})|\\d+(?:[\\.,]\\d{2})?)");

    public static boolean containsForbidden(String t) {
        for (String f : FORBIDDEN) {
            if (t.contains(f)) return true;
        }
        return false;
    }

    public static Float extractValueIfAnchored(String t) {
        String lt = t.toLowerCase(Locale.ROOT);

        if (!containsAny(lt, PRIMARY) && !containsAny(lt, SECONDARY)) return null;

        Matcher m = MONEY.matcher(lt);
        if (!m.find()) return null;

        String raw = m.group(2)
                .replace(".", "")
                .replace(",", ".");

        try {
            return Float.parseFloat(raw);
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean containsAny(String t, String[] arr) {
        for (String a : arr) {
            if (t.contains(a)) return true;
        }
        return false;
    }
}
