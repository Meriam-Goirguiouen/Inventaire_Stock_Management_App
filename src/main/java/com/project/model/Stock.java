package com.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock") 
    private Integer idStock;

    @OneToOne
    @JoinColumn(name = "id_article", unique = true) 
    private Article article;

    @Column(name = "quantite_actuelle")
    private int quantiteActuelle;

    @Column(name = "seuil_alerte")
    private int seuilAlerte;

    @Column(name = "derniere_miseajour")
    private LocalDateTime derniereMiseAJour;

    // Getters et Setters

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    // Getter et Setter pour l'objet Article
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantiteActuelle() {
        return quantiteActuelle;
    }

    public void setQuantiteActuelle(int quantiteActuelle) {
        this.quantiteActuelle = quantiteActuelle;
    }

    public int getSeuilAlerte() {
        return seuilAlerte;
    }

    public void setSeuilAlerte(int seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

    public LocalDateTime getDerniereMiseAJour() {
        return derniereMiseAJour;
    }

    public void setDerniereMiseAJour(LocalDateTime derniereMiseAJour) {
        this.derniereMiseAJour = derniereMiseAJour;
    }
}