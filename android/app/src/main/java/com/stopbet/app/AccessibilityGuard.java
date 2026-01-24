package com.stopbet.app;

import android.content.Context;
import android.provider.Settings;

public class AccessibilityGuard {

    public static boolean isEnabled(Context ctx) {
        try {
            String enabled = Settings.Secure.getString(
                    ctx.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            );
            if (enabled == null) return false;

            String service = ctx.getPackageName() + "/" +
                    KairosAccessibilityService.class.getName();

            return enabled.contains(service);

        } catch (Exception e) {
            return false;
        }
    }
}
