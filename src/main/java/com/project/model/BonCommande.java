package com.project.model; // Assurez-vous que le package est le bon

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bon_commande") // Spécifier le nom de la table
public class BonCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // La colonne 'id' est BIGINT(20), donc Long est correct ici.

    @Column(name = "date_creation") // Lien avec la colonne
    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat") // Lien avec la colonne
    private EtatCommande etat;

    @ManyToOne(fetch = FetchType.LAZY) // fetch=LAZY est une bonne pratique
    @JoinColumn(name = "fournisseur_id", referencedColumnName = "id_utilisateur") // CORRECTION ICI
    private Utilisateur fournisseur;

    @OneToMany(mappedBy = "bonCommande", cascade = CascadeType.ALL, orphanRemoval = true)
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
