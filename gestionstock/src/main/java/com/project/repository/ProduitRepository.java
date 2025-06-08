package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.model.Produit;
import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long>{
   List<Produit> findByQuantiteLessThanEqual(int seuil);  
}
