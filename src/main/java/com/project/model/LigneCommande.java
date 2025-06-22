package com.project.model;

import jakarta.persistence.*;

@Entity
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // private Produit produit;

    private int quantite;


    @ManyToOne
    private BonCommande bonCommande;

    // Getters & Setters
    public Long getId() { 
        return id; 
    }

    // public Produit getProduit() { 
    //     return produit; 
    // }
    // public void setProduit(Produit produit) { 
    //     this.produit = produit; 
    // }

    public int getQuantite() { 
        return quantite; 
    }
    public void setQuantite(int quantite) { 
        this.quantite = quantite; 
    }

    public BonCommande getBonCommande() { 
        return bonCommande; 
    }
    public void setBonCommande(BonCommande bonCommande) { 
        this.bonCommande = bonCommande; 
    }
}
