package com.projetstock.gestionstock.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @JmsListener(destination = "stock-alert")
    public void recevoirNotification(String message) {
        System.out.println(" Notification reçue : " + message);
    }
}

