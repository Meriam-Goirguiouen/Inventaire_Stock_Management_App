package com.projetstock.repository;

import com.projetstock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findByIdArticle(int idArticle);
}

