package com.projetstock.service;

import com.projetstock.model.MouvementStock;
import com.projetstock.model.Article; 
import com.projetstock.model.Stock;
import com.projetstock.repository.ArticleRepository;
import com.projetstock.repository.MouvementStockRepository;
import com.projetstock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class MouvementStockService {

    @Autowired
    private MouvementStockRepository mouvementStockRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ArticleRepository articleRepository; // C'est bien que ce soit là

    public void enregistrerMouvement(MouvementStock mouvement) {
        // Enregistrer le mouvement
        mouvement.setDateMouvement(LocalDateTime.now());
        mouvementStockRepository.save(mouvement);

        // Mettre à jour le stock
        // Note: C'est mieux d'utiliser findById qui retourne un Optional
        Stock stock = stockRepository.findByIdArticle(mouvement.getIdArticle()); 
        if (stock != null) {
            int nouvelleQuantite = stock.getQuantiteActuelle();

            if (mouvement.getTypeMouvement().equalsIgnoreCase("ENTREE")) {
                nouvelleQuantite += mouvement.getQuantiteModifiee();
            } else if (mouvement.getTypeMouvement().equalsIgnoreCase("SORTIE")) {
                nouvelleQuantite -= mouvement.getQuantiteModifiee();
            }

            stock.setQuantiteActuelle(nouvelleQuantite);
            stock.setDerniereMiseAJour(LocalDateTime.now());
            stockRepository.save(stock);

            // ==================== DÉBUT DE LA CORRECTION ====================

            // Vérifier seuil d’alerte
            if (nouvelleQuantite < stock.getSeuilAlerte()) {
                
                // 1. On utilise l'ID de l'article du mouvement pour trouver l'article complet
                Article articleConcerne = articleRepository.findById(mouvement.getIdArticle())
                        .orElseThrow(() -> new RuntimeException("Erreur: Impossible de trouver l'article avec l'ID " + mouvement.getIdArticle() + " pour envoyer la notification."));

                // 2. On appelle la bonne méthode du NotificationService avec le nom de l'article
                // L'ID '1' pour l'utilisateur est un exemple, à adapter si besoin (ex: récupérer l'admin)
                notificationService.envoyerNotificationStockBas(1, articleConcerne.getNom());
            }
        }
    }
}