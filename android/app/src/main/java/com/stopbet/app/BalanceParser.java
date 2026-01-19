package com.stopbet.app;

import android.view.accessibility.AccessibilityNodeInfo;
import java.util.regex.*;

public class BalanceParser {

    private static final Pattern MONEY =
            Pattern.compile("(R\\$\\s*)?(\\d+[\\.,]\\d{2})");

    public static Double extract(AccessibilityNodeInfo node) {

        if (node == null) return null;

        CharSequence text = node.getText();
        if (text != null) {
            Matcher m = MONEY.matcher(text.toString());
            if (m.find()) {
                String raw = m.group(2).replace(",", ".");
                try {
                    return Double.parseDouble(raw);
                } catch (Exception ignored) {}
            }
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            Double v = extract(node.getChild(i));
            if (v != null) return v;
        }

        return null;
    }
}
