package com.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class BonCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    private EtatCommande etat;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Utilisateur fournisseur;

    @OneToMany(mappedBy = "bonCommande", cascade = CascadeType.ALL)
    private List<LigneCommande> lignes;

    public enum EtatCommande {
        EN_ATTENTE,
        CONFIRMEE,
        REFUSEE,
        LIVREE
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public EtatCommande getEtat() {
        return etat;
    }

    public void setEtat(EtatCommande etat) {
        this.etat = etat;
    }

    public Utilisateur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Utilisateur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneCommande> lignes) {
        this.lignes = lignes;
    }
}
