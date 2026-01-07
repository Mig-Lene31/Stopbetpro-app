package com.stopbet.app;

import android.content.Context;

public class DailyTimeEngine {

    public static void addMinute(Context ctx) {
        // reservado para futura contagem real
    }

    public static boolean exceeded(Context ctx) {
        return TimeStore.hasTimeLimit(ctx) &&
               TimeStore.getUsedMinutesToday(ctx) >= TimeStore.getMinutes(ctx);
    }
}
