package com.stopbet.app;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

public class AccessibilityGuard {

    public static boolean isEnabled(Context ctx) {
        try {
            String enabled = Settings.Secure.getString(
                    ctx.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            );
            if (enabled == null) return false;

            String expected = ctx.getPackageName() + "/.KairosAccessibilityService";
            return enabled.contains(expected);

        } catch (Exception e) {
            return false;
        }
    }
}
