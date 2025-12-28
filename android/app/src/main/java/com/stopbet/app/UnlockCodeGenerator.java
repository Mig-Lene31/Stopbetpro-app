package com.stopbet.app;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UnlockCodeGenerator {

    private static final String SECRET = "STOPBET_PRO_SECRET";

    public static String generate(String userId) {
        try {
            String date = new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date());
            String base = userId + date + SECRET;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(base.getBytes());

            String hex = bytesToHex(hash).toUpperCase();

            return "SB-" +
                    hex.substring(0,4) + "-" +
                    hex.substring(4,8) + "-" +
                    hex.substring(8,12);

        } catch (Exception e) {
            return "ERROR";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
