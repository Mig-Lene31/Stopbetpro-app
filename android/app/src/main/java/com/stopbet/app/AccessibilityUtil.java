package com.stopbet.app;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

public class AccessibilityUtil {

    public static boolean isServiceEnabled(Context ctx) {
        String enabled = Settings.Secure.getString(
                ctx.getContentResolver(),
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        );
        if (enabled == null) return false;

        return enabled.contains(ctx.getPackageName() + "/.BetBlockAccessibilityService");
    }
}
