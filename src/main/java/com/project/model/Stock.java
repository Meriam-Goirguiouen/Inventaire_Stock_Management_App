package com.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock") // Bonne pratique de spécifier le nom de la table
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock") // Lien avec la colonne id_stock
    private Integer idStock; // Utiliser Integer (objet) est plus sûr que int (primitif)

    // ================== LA CORRECTION EST ICI ==================
    // On remplace le simple "idArticle" par une vraie relation objet.
    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_article", unique = true) // 'id_article' est la colonne de la clé étrangère
    private Article article;
    // ========================================================

    @Column(name = "quantite_actuelle")
    private int quantiteActuelle;

    @Column(name = "seuil_alerte")
    private int seuilAlerte;

    @Column(name = "derniere_mise_a_jour")
    private LocalDateTime derniereMiseAJour;

    // --- Getters et Setters mis à jour ---

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    // Le getter et setter pour l'ID de l'article sont maintenant remplacés
    // par ceux pour l'objet Article.
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