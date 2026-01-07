package com.stopbet.app;

import android.content.Context;

public class AppProtectionState {

    public static boolean isActive(Context ctx) {
        if (!MotorState.isEnabled(ctx)) return false;
        if (EngineState.isBlocked(ctx)) return false;
        return true;
    }

    public static String getStatusLabel(Context ctx) {
        if (EngineState.isBlocked(ctx)) {
            return "BLOQUEADO";
        }
        if (!MotorState.isEnabled(ctx)) {
            return "DESATIVADO";
        }
        return "ATIVO";
    }
}
