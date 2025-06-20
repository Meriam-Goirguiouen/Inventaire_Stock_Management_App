package com.projetstock.gestionstock;

import com.projetstock.gestionstock.jms.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionstockApplication implements CommandLineRunner {

    @Autowired
    private NotificationProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(GestionstockApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Simulation d’une alerte de stock bas
        producer.envoyerNotification("Stock bas pour le produit: Laptop HP 15s !");
    }
}
