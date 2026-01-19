package com.stopbet.app.runtime;

import android.content.Context;
import com.stopbet.app.domain.*;

public class EngineRuntimeCore {

    public static ProtectionDecision check(Context ctx, ProtectionInput input) {
        return ProtectionRules.evaluate(input);
    }
}
