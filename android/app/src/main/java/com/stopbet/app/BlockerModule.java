package com.stopbet.app;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BlockerModule extends ReactContextBaseJavaModule {

    private final BlockRulesManager rulesManager;

    public BlockerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        rulesManager = new BlockRulesManager(reactContext);
    }

    @Override
    public String getName() {
        return "StopBetBlocker";
    }

    /* ===============================
       MÉTODOS EXPOSTOS AO REACT NATIVE
       =============================== */

    @ReactMethod
    public void setDeposit(double value) {
        rulesManager.saveDeposit(value);
    }

    @ReactMethod
    public void setStopWin(double value) {
        rulesManager.saveStopWin(value);
    }

    @ReactMethod
    public void setStopLoss(double value) {
        rulesManager.saveStopLoss(value);
    }

    @ReactMethod
    public void enableBlock(boolean active) {
        rulesManager.setBlockActive(active);
    }
}
