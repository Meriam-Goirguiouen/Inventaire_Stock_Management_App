package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.model.LigneCommande;
import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
    List<LigneCommande> findByBonCommandeId(Long bonCommandeId);
}