package com.project.gestionstock;

import com.project.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class GestionstockApplication implements CommandLineRunner {

    // On injecte le SERVICE, pas le producer
    @Autowired
    private NotificationService notificationService; 

    public static void main(String[] args) {
        SpringApplication.run(GestionstockApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // On appelle la méthode du service pour notre test
        System.out.println("--- DÉBUT DU TEST D'ENVOI JMS ---");
        // L'id utilisateur '1' est un exemple, pour représenter l'admin
        notificationService.envoyerNotificationStockBas(1, "Laptop HP 15s (Test au démarrage)");
        System.out.println("--- FIN DU TEST D'ENVOI JMS ---");
    }

}
