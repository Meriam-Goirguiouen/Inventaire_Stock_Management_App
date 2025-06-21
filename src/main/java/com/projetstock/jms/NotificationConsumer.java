package com.projetstock.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    // Cette méthode écoute en permanence la queue spécifiée
    @JmsListener(destination = "alerte.stock.bas.queue")
    public void receiveMessage(String message) {
        // Quand un message arrive, cette méthode est appelée automatiquement
        System.out.println("<<<< MESSAGE JMS REÇU : " + message + " >>>>");
        // Ici, plus tard, on pourrait envoyer un email, un SMS, une notif push, etc.
    }
}