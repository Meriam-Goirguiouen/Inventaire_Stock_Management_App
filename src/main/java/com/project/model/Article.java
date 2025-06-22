package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArticle;
    
    private String nom;
    private String description;
    private String categorie;
    // Ajoutez d'autres champs si nécessaire (catégorie, etc.)

    // Getters et Setters...
    public Integer getIdArticle() { return idArticle; }
    public void setIdArticle(Integer idArticle) { this.idArticle = idArticle; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
}