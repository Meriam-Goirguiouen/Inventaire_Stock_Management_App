package com.project.service;

import com.project.model.MouvementStock;
import com.project.model.Article; 
import com.project.model.Stock;
import com.project.repository.ArticleRepository;
import com.project.repository.MouvementStockRepository;
import com.project.repository.StockRepository;
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
       
        // Récupérer l'article à partir du mouvement
        Article articleConcerne = mouvement.getArticle();
        if (articleConcerne == null) {
            throw new RuntimeException("Le mouvement de stock n'est pas lié à un article.");
        }
       // Mettre à jour le stock en utilisant la méthode corrigée du repository
        Stock stock = stockRepository.findByArticle_IdArticle(articleConcerne.getIdArticle());
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

    // 1. On récupère directement l'objet Article depuis le mouvement. Pas de nouvelle requête !
    Article articleConcernee = mouvement.getArticle();

    // 2. On appelle la bonne méthode du NotificationService
    //    L'ID '1' pour l'utilisateur est un exemple, à adapter si besoin (ex: récupérer l'admin)
    notificationService.envoyerNotificationStockBas(1, articleConcernee.getNom());
}
            }
        }
    }