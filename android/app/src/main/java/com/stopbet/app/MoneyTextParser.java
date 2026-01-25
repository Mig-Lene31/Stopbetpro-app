package com.stopbet.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoneyTextParser {

    private static final Pattern MONEY_PATTERN =
            Pattern.compile("(R\\$|BRL)?\\s*([0-9]{1,3}(\\.[0-9]{3})*|[0-9]+)(,[0-9]{2}|\\.[0-9]{2})");

    public static Double extract(String text) {
        if (text == null) return null;

        Matcher m = MONEY_PATTERN.matcher(text);
        if (!m.find()) return null;

        String value = m.group()
                .replace("R$", "")
                .replace("BRL", "")
                .replace(".", "")
                .replace(",", ".")
                .trim();

        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return null;
        }
    }
}
