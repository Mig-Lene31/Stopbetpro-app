package com.stopbet.app;

public class InterventionMessageEngine {

    public static String messageForApp(String packageName) {
        return
                "Respira um pouco.\n\n" +
                "Você tentou abrir um aplicativo de apostas.\n\n" +
                "O Kairós entrou em ação para te ajudar a manter o controle.\n\n" +
                "Isso não é punição. É proteção.";
    }

    public static String messageForDomain(String domain) {
        return
                "Calma.\n\n" +
                "Um site de apostas foi detectado:\n" + domain + "\n\n" +
                "Esse bloqueio existe para te dar espaço e tempo.\n\n" +
                "Você continua no controle.";
    }
}
