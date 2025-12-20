package com.stopbet.app.rules;

public class RuleEngine {

    private static long startTime = 0;
    private static long maxTimeMillis = 0;
    private static double initial = 0;
    private static double stopWin = 0;
    private static double stopLoss = 0;

    public static void start(
            long maxTimeSeconds,
            double initialValue,
            double sw,
            double sl
    ) {
        startTime = System.currentTimeMillis();
        maxTimeMillis = maxTimeSeconds * 1000;
        initial = initialValue;
        stopWin = sw;
        stopLoss = sl;
    }

    public static boolean check(double current) {
        if (startTime == 0) return false;

        if (maxTimeMillis > 0 &&
                System.currentTimeMillis() - startTime >= maxTimeMillis) {
            return true;
        }

        if (stopWin > 0 && current >= stopWin) return true;
        if (stopLoss > 0 && current <= stopLoss) return true;

        return false;
    }

    public static void reset() {
        startTime = 0;
    }
}
