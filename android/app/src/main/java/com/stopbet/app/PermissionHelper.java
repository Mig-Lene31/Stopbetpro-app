package com.stopbet.app;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class PermissionHelper {

    public static void openAccessibility(Context ctx) {
        Intent i = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }
}
