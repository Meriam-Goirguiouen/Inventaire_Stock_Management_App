package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Active le serveur de messagerie WebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // C'est le "point d'entrée" de la connexion WebSocket pour les clients.
        // L'URL de connexion sera "http://localhost:8080/ws".
        // withSockJS() offre une solution de repli pour les navigateurs qui ne supportent pas les WebSockets.
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Définit le préfixe pour les destinations de messages qui sont routées
        // vers des méthodes @MessageMapping dans les contrôleurs (client -> serveur).
        registry.setApplicationDestinationPrefixes("/app");
        
        // Définit le préfixe pour les destinations du broker de messages simple.
        // Les messages envoyés à des destinations commençant par "/topic"
        // seront envoyés à tous les clients abonnés.
        // C'est ce que nous allons utiliser pour diffuser nos alertes.
        registry.enableSimpleBroker("/topic");
    }
}
