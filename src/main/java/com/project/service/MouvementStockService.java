package com.project.service;

import com.project.dto.MouvementStockDto;
import com.project.model.Article;
import com.project.model.MouvementStock;
import com.project.model.Stock;
import com.project.repository.ArticleRepository;
import com.project.repository.MouvementStockRepository;
import com.project.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ArticleRepository articleRepository;

    @Transactional
    public void enregistrerMouvement(MouvementStockDto mouvementDto) {
        
        // ==================== PARTIE 1 : TRADUCTION DTO -> ENTITÉ (Fusionnée) ====================
        
        // On vérifie que l'article existe (votre logique robuste)
        Article articleConcerne = articleRepository.findById(mouvementDto.getIdArticle())
                .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID : " + mouvementDto.getIdArticle()));

        // On crée l'entité MouvementStock
        MouvementStock mouvement = new MouvementStock();
        
        // **LA FUSION EST ICI** : On lie l'objet Article entier, pas seulement son ID.
        // Cela suppose que l'entité MouvementStock a un champ 'private Article article;'
        mouvement.setArticle(articleConcerne); 
        
        mouvement.setTypeMouvement(mouvementDto.getTypeMouvement());
        mouvement.setQuantiteModifiee(mouvementDto.getQuantiteModifiee());
        mouvement.setDateMouvement(LocalDateTime.now());
        
        mouvementStockRepository.save(mouvement);

        // ==================== PARTIE 2 : LOGIQUE MÉTIER (Votre version robuste) ====================
        
        // On utilise la méthode de recherche de votre collègue si elle est plus propre
        Stock stock = stockRepository.findByArticle_IdArticle(articleConcerne.getIdArticle());
        
        if (stock == null) {
            throw new RuntimeException("Aucun stock trouvé pour l'article : " + articleConcerne.getNom());
        }

        int nouvelleQuantite = stock.getQuantiteActuelle();

        if ("ENTREE".equalsIgnoreCase(mouvement.getTypeMouvement())) {
            nouvelleQuantite += mouvement.getQuantiteModifiee();
        } else if ("SORTIE".equalsIgnoreCase(mouvement.getTypeMouvement())) {
            nouvelleQuantite -= mouvement.getQuantiteModifiee();
            if (nouvelleQuantite < 0) {
                throw new RuntimeException("Quantité en stock insuffisante pour l'article : " + articleConcerne.getNom());
            }
        }

        stock.setQuantiteActuelle(nouvelleQuantite);
        stock.setDerniereMiseAJour(LocalDateTime.now());
        stockRepository.save(stock);

        // Vérifier seuil d’alerte
        if ("SORTIE".equalsIgnoreCase(mouvement.getTypeMouvement()) && nouvelleQuantite < stock.getSeuilAlerte()) {
            notificationService.envoyerNotificationStockBas(1, articleConcerne.getNom());
        }
    }
}