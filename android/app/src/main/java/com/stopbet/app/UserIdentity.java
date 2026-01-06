package com.stopbet.app;

import android.content.Context;
import android.provider.Settings;

public class UserIdentity {

    public static String getId(Context ctx) {
        return Settings.Secure.getString(
            ctx.getContentResolver(),
            Settings.Secure.ANDROID_ID
        );
    }
}
