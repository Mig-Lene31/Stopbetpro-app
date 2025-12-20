package com.stopbetpro;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class StopAccessibilityModule extends ReactContextBaseJavaModule {

    private static ReactApplicationContext reactContext;

    public StopAccessibilityModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @Override
    public String getName() {
        return "StopAccessibility";
    }

    // Envia texto capturado para o JS
    public static void sendTextToJS(String text) {
        if (reactContext != null) {
            reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("StopAccessibilityText", text);
        }
    }
}
