package com.project.service;

import com.project.model.Notification;
import com.project.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.jms.core.JmsTemplate; // <-- Importer JmsTemplate
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JmsTemplate jmsTemplate; // <-- Injection de JmsTemplate

    // Le nom de notre "boîte aux lettres" (queue) dans Artemis
    private static final String NOTIFICATION_QUEUE = "alerte.stock.bas.queue";

    
    public void envoyerNotificationStockBas(Integer idUtilisateur, String nomArticle) {
        // 1. Préparer le message
        String messageText = "Alerte : le stock de l'article '" + nomArticle + "' est bas.";

        // 2. Créer l'entité Notification pour la base de données (ce que vous faisiez déjà)
        Notification notification = new Notification();
        notification.setIdUtilisateur(idUtilisateur); // Par exemple, l'ID de l'admin
        notification.setMessage(messageText);
        notification.setDateNotification(LocalDate.now());
        notification.setTypeNotification("STOCK_BAS");

        // 3. Sauvegarder en BDD pour l'historique
        notificationRepository.save(notification);
        System.out.println("Notification sauvegardée en BDD.");

        // 4. ENVOYER le message via JMS
        // On envoie un message simple (le texte) dans la queue "alerte.stock.bas.queue"
        jmsTemplate.convertAndSend(NOTIFICATION_QUEUE, messageText);
        System.out.println("Message JMS envoyé à la queue : " + NOTIFICATION_QUEUE);
    }
}