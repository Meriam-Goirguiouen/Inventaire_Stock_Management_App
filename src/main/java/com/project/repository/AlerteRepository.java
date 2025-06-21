package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.model.Alerte;
import com.project.model.Produit;
import java.util.List;

public interface AlerteRepository extends JpaRepository<Alerte, Long> {
     List<Alerte> findByProduit(Produit produit); 
}