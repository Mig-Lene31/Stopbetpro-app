package com.stopbetpro;

import android.content.Intent;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BlockOverlayModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext context;

    public BlockOverlayModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @Override
    public String getName() {
        return "BlockOverlay";
    }

    @ReactMethod
    public void show(String message) {
        Intent intent = new Intent(context, BlockOverlayService.class);
        intent.putExtra("message", message);
        context.startService(intent);
    }

    @ReactMethod
    public void hide() {
        Intent intent = new Intent(context, BlockOverlayService.class);
        context.stopService(intent);
    }
}
