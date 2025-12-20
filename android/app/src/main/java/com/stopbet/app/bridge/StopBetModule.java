package com.stopbet.app.bridge;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import com.stopbet.app.blocker.BlockController;
import com.stopbet.app.blocker.BlockState;

public class StopBetModule extends ReactContextBaseJavaModule {

    public StopBetModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "StopBetEngine";
    }

    @ReactMethod
    public void enable(
            int maxTimeSeconds,
            double initialValue,
            double stopWin,
            double stopLoss
    ) {
        BlockController.setEnabled(true);
        BlockController.startRules(
                maxTimeSeconds,
                initialValue,
                stopWin,
                stopLoss
        );
    }

    @ReactMethod
    public void updateValue(double value) {
        BlockController.updateValue(value);
    }

    @ReactMethod
    public void isBlocked(Promise promise) {
        promise.resolve(BlockController.isBlocked());
    }

    @ReactMethod
    public void remainingSeconds(Promise promise) {
        long r = BlockState.remainingSeconds(getReactApplicationContext());
        promise.resolve((int) r);
    }

    // 💰 PAGAMENTO CONFIRMADO
    @ReactMethod
    public void unlockPaid(Promise promise) {
        BlockController.unlockPaid();
        promise.resolve(true);
    }

    @ReactMethod
    public void disable() {
        if (BlockController.isBlocked()) return;
        BlockController.setEnabled(false);
    }
}
