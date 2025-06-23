package com.project.repository;

import com.project.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findByArticle_IdArticle(Integer idArticle);
    @Query("SELECT count(s) FROM Stock s WHERE s.quantiteActuelle < s.seuilAlerte")
    long countLowStockItems();
}

