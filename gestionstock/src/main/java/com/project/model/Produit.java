package com.project.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Produit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int quantite;
    private int seuilCritique;
    
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie; 

    @OneToMany(mappedBy = "produit")
    private List<Alerte> alertes;


    // Getters & Setters
    public Long getId(){
        return this.id;
    }
    public String getNom(){
        return this.nom;
    }
    public int getQuantite(){
        return this.quantite;
    }
    public int getSeuilC(){
        return this.seuilCritique;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setQuantite(int qte){
        this.quantite = qte;
    }
    public void setSeuilC(int seuilC){
        this.seuilCritique = seuilC;
    } 

    
}
