package com.stopbet.app;

public class BalanceContextAnalyzer {

    public static boolean looksLikeBalanceRegion(int x, int y, int w, int h, int screenW, int screenH) {

        boolean topo = y < screenH * 0.25;
        boolean canto = x < screenW * 0.5;
        boolean tamanhoOk = w > 80 && h < 200;

        return topo && canto && tamanhoOk;
    }
}
