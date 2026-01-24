package com.stopbet.app;

public class InterventionMessageEngine {

    public static String messageForApp(String packageName, int count) {

        if (count == 1) {
            return
                    "Respira.\n\n" +
                    "Você tentou abrir um aplicativo de apostas.\n\n" +
                    "O Kairós entrou em ação para te ajudar a manter o controle.\n\n" +
                    "Isso é cuidado, não punição.";
        }

        if (count <= 3) {
            return
                    "Atenção.\n\n" +
                    "Você voltou a tentar acessar apostas.\n\n" +
                    "Esse impulso passa. Dá um tempo agora.\n\n" +
                    "O Kairós está te protegendo.";
        }

        return
                "Pare um momento.\n\n" +
                "Várias tentativas seguidas foram detectadas.\n\n" +
                "Isso indica risco real.\n\n" +
                "Afasta-se agora. Essa decisão é por você.";
    }

    public static String messageForDomain(String domain, int count) {

        if (count == 1) {
            return
                    "Calma.\n\n" +
                    "Um site de apostas foi detectado:\n" + domain + "\n\n" +
                    "Esse bloqueio existe para te dar espaço.";
        }

        if (count <= 3) {
            return
                    "Cuidado.\n\n" +
                    "Você voltou a acessar sites de aposta.\n\n" +
                    "Respira. Sai disso agora.";
        }

        return
                "Alerta.\n\n" +
                "Múltiplas tentativas em pouco tempo.\n\n" +
                "Isso é um sinal claro de risco.\n\n" +
                "O Kairós não vai permitir continuar.";
    }
}
