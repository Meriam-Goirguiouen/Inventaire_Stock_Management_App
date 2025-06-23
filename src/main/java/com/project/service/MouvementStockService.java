package com.project.service;

import com.project.dto.MouvementStockDto; // <-- Importer le DTO
import com.project.model.Article;
import com.project.model.MouvementStock;
import com.project.model.Stock;
import com.project.repository.ArticleRepository;
import com.project.repository.MouvementStockRepository;
import com.project.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- Important pour la cohérence

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MouvementStockService {

    @Autowired
    private MouvementStockRepository mouvementStockRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ArticleRepository articleRepository;

    // L'annotation @Transactional garantit que soit TOUT réussit, soit RIEN n'est sauvegardé.
    // C'est crucial pour la cohérence des données.
    @Transactional
    public void enregistrerMouvement(MouvementStockDto mouvementDto) {
        
        // ==================== PARTIE 1 : TRADUCTION DTO -> ENTITÉ ====================
        
        // On vérifie que l'article existe avant de continuer.
        Article articleConcerne = articleRepository.findById(mouvementDto.getIdArticle())
                .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID : " + mouvementDto.getIdArticle()));

        // On crée l'entité MouvementStock qui sera sauvegardée en BDD.
        MouvementStock mouvement = new MouvementStock();
        mouvement.setIdArticle(articleConcerne.getIdArticle()); // On utilise l'ID de l'article trouvé
        mouvement.setTypeMouvement(mouvementDto.getTypeMouvement());
        mouvement.setQuantiteModifiee(mouvementDto.getQuantiteModifiee());
        mouvement.setDateMouvement(LocalDateTime.now());
        
        mouvementStockRepository.save(mouvement);

        // ==================== PARTIE 2 : LOGIQUE MÉTIER EXISTANTE ====================
        // Le reste du code est votre logique, mais rendue plus robuste.
        
        Stock stock = stockRepository.findByIdArticle(articleConcerne.getIdArticle());
        
        if (stock == null) {
            // Si le stock n'existe pas pour cet article, c'est une erreur de données.
            // On pourrait aussi décider d'en créer un nouveau ici.
            throw new RuntimeException("Aucun stock trouvé pour l'article : " + articleConcerne.getNom());
        }

        int nouvelleQuantite = stock.getQuantiteActuelle();

        if ("ENTREE".equalsIgnoreCase(mouvement.getTypeMouvement())) {
            nouvelleQuantite += mouvement.getQuantiteModifiee();
        } else if ("SORTIE".equalsIgnoreCase(mouvement.getTypeMouvement())) {
            nouvelleQuantite -= mouvement.getQuantiteModifiee();
            if (nouvelleQuantite < 0) {
                // On ne peut pas avoir un stock négatif. On lève une erreur.
                throw new RuntimeException("Quantité en stock insuffisante pour l'article : " + articleConcerne.getNom());
            }
        }

        stock.setQuantiteActuelle(nouvelleQuantite);
        stock.setDerniereMiseAJour(LocalDateTime.now());
        stockRepository.save(stock);

        // Vérifier seuil d’alerte (uniquement pour les sorties)
        if ("SORTIE".equalsIgnoreCase(mouvement.getTypeMouvement()) && nouvelleQuantite < stock.getSeuilAlerte()) {
            
            // L'ID '1' pour l'utilisateur est un exemple, à adapter si besoin.
            // On a déjà 'articleConcerne', donc pas besoin de le rechercher à nouveau.
            notificationService.envoyerNotificationStockBas(1, articleConcerne.getNom());
        }
    }
}