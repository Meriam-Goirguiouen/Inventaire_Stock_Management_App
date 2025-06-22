package com.project.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    @OneToMany(mappedBy = "categorie")
    // private List<Produit> produits;

    // Getters & Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    // public List<Produit> getProduits() {
    //     return produits;
    // }

    // public void setProduits(List<Produit> produits) {
    //     this.produits = produits;
    // }
}
