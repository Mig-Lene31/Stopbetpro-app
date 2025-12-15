package com.stopbet.app;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.Arrays;
import java.util.List;

public class BlockAccessibilityService extends AccessibilityService {

    private final List<String> bettingKeywords = Arrays.asList(
            "bet", "casino", "cassino", "aposta", "odds",
            "roleta", "slots", "blaze", "betano",
            "sportingbet", "pixbet", "1xbet",
            "betfair", "bet365"
    );

    @Override
    protected void onServiceConnected() {
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes =
                AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED |
                AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.notificationTimeout = 100;
        setServiceInfo(info);

        Log.d("STOPBET", "Serviço de acessibilidade conectado");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) return;

        BlockRulesManager rules =
                BlockRulesManager.getInstance(this);

        // 🔒 Se bloqueio de 12h estiver ativo, bloqueia tudo
        if (rules.isBlockActive()) {
            openBlockScreen();
            return;
        }

        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) return;

        scanNode(root);
    }

    private void scanNode(AccessibilityNodeInfo node) {
        if (node == null) return;

        CharSequence text = node.getText();
        CharSequence desc = node.getContentDescription();

        if (containsBettingKeyword(text) || containsBettingKeyword(desc)) {

            // 🚨 Detectou site de apostas → ativa bloqueio de 12h
            BlockRulesManager
                    .getInstance(this)
                    .activateBlock();

            openBlockScreen();
            return;
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            scanNode(node.getChild(i));
        }
    }

    private boolean containsBettingKeyword(CharSequence value) {
        if (value == null) return false;
        String lower = value.toString().toLowerCase();
        for (String keyword : bettingKeywords) {
            if (lower.contains(keyword)) return true;
        }
        return false;
    }

    private void openBlockScreen() {
        Intent intent = new Intent(this, BlockActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onInterrupt() {}
}
