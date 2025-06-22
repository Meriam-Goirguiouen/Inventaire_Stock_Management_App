package com.project.repository;

import com.project.model.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Integer> {
}

