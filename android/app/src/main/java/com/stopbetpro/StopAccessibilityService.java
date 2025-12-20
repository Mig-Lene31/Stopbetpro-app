package com.stopbetpro;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StopAccessibilityService extends AccessibilityService {

    private static final String TAG = "StopAccessibility";

    // Regex para valores monetários (R$ opcional)
    private static final Pattern MONEY_PATTERN =
            Pattern.compile("(R\\$\\s?)?\\d{1,5}([.,]\\d{2})?");

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) return;

        List<String> texts = new ArrayList<>();
        extractText(root, texts);

        for (String text : texts) {
            if (isPossibleBalance(text)) {
                Log.d(TAG, "POSSÍVEL SALDO: " + text);

                // 🔥 Envia apenas candidatos válidos para o React Native
                StopAccessibilityModule.sendTextToJS(text);
            }
        }
    }

    private void extractText(AccessibilityNodeInfo node, List<String> texts) {
        if (node == null) return;

        if (node.getText() != null) {
            texts.add(node.getText().toString());
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            extractText(node.getChild(i), texts);
        }
    }

    // 🔒 HARDEN — filtro anti-ranking / odds / listas
    private boolean isPossibleBalance(String text) {
        if (text == null) return false;

        String t = text.toLowerCase();

        // Palavras típicas de lixo de site de apostas
        if (t.contains("ganhador") ||
            t.contains("ranking") ||
            t.contains("odds") ||
            t.contains("aposta") ||
            t.contains("rodada") ||
            t.contains("placar") ||
            t.contains("multiplicador")) {
            return false;
        }

        // Muito longo = histórico / lista
        if (t.length() > 15) return false;

        // Muito curto = ruído
        if (t.length() < 2) return false;

        // Precisa parecer dinheiro
        return MONEY_PATTERN.matcher(text).find();
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "Accessibility interrompido");
    }
}
