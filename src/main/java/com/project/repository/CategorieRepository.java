package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}