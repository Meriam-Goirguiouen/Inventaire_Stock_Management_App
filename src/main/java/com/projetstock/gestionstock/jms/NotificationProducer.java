package com.projetstock.gestionstock.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void envoyerNotification(String message) {
        jmsTemplate.convertAndSend("stock-alert", message);
        System.out.println(" Notification envoyée : " + message);
    }
}
