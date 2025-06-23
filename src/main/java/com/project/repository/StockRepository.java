package com.project.repository;

import com.project.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository; // C'est une bonne pratique d'ajouter @Repository

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    /**
     * Trouve une entrée de stock en se basant sur l'ID de l'article associé.
     * C'est la méthode standard de Spring Data pour naviguer dans les relations.
     * @param idArticle L'ID de l'article recherché.
     * @return L'objet Stock correspondant, ou null s'il n'est pas trouvé.
     */
    Stock findByArticle_IdArticle(Integer idArticle);

    /**
     * Compte le nombre d'articles dont la quantité actuelle est inférieure au seuil d'alerte.
     * @return Le nombre total d'articles en stock bas.
     */
    @Query("SELECT count(s) FROM Stock s WHERE s.quantiteActuelle < s.seuilAlerte")
    long countLowStockItems();
}