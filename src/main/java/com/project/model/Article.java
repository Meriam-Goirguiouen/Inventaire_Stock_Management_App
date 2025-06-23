package com.project.model;

import jakarta.persistence.*; // Important d'avoir les bons imports

@Entity
@Table(name = "article") // Bonne pratique pour lier au nom de la table
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article") // Correspond à la colonne de la BDD
    private Integer idArticle;
    
    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "categorie")
    private String categorie;

    // ================== LA CORRECTION EST ICI ==================
    // On ajoute la relation inverse vers l'entité Stock.
    // 'mappedBy = "article"' est crucial pour indiquer que l'entité Stock est propriétaire de la relation.
    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Stock stock;
    // ========================================================


    // --- Getters et Setters (avec l'ajout pour le stock) ---

    public Integer getIdArticle() { 
        return idArticle; 
    }
    public void setIdArticle(Integer idArticle) { 
        this.idArticle = idArticle; 
    }

    public String getNom() { 
        return nom; 
    }
    public void setNom(String nom) { 
        this.nom = nom; 
    }

    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }

    public String getCategorie() { 
        return categorie; 
    }
    public void setCategorie(String categorie) { 
        this.categorie = categorie; 
    }
    
    // Getter et Setter pour la nouvelle relation
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}