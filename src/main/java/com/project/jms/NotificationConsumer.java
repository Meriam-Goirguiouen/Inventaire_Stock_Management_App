package com.project.jms; // Assurez-vous que le package est le bon

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate; // <-- L'import pour l'outil d'envoi
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    // On injecte le template de messagerie qui sait parler aux WebSockets
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Cette méthode écoute la queue JMS interne pour les alertes de stock bas.
     * Une fois qu'un message est reçu, elle le relaie vers tous les clients
     * connectés via WebSocket sur le topic "/topic/stock_alerts".
     *
     * @param message Le message texte de l'alerte.
     */
    @JmsListener(destination = "alerte.stock.bas.queue")
    public void receiveMessage(String message) {
        
        // On garde cette ligne pour le débogage côté serveur
        System.out.println("<<<< MESSAGE JMS REÇU : " + message + " >>>>");

        // C'est ici que la magie opère : on relaie le message au frontend.
        // Le message sera envoyé sur le canal "/topic/stock_alerts".
        // Le frontend devra s'abonner à ce canal pour le recevoir.
        messagingTemplate.convertAndSend("/topic/stock_alerts", message);

        System.out.println("---- Message relayé au Frontend via WebSocket sur /topic/stock_alerts ----");
    }
}