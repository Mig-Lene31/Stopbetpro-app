package com.stopbet.app;

public class DnsPacketParser {

    public static String extractHostname(byte[] packet) {
        try {
            if (packet == null || packet.length < 12) return null;

            int index = 12; // DNS header ends here
            StringBuilder host = new StringBuilder();

            while (index < packet.length) {
                int len = packet[index++] & 0xFF;
                if (len == 0) break;
                if (index + len > packet.length) return null;

                if (host.length() > 0) host.append(".");
                for (int i = 0; i < len; i++) {
                    host.append((char) packet[index++]);
                }
            }

            return host.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
