package com.project.model; 

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur") // On spécifie explicitement le nom de la table
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur") // Lien avec la colonne 'id_utilisateur'
    private Integer id; // Le type en BDD est INT, donc on utilise Integer

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom") // La colonne 'prenom' existe bien
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "mot_de_passe") // Le nom de la colonne est 'mot_de_passe'
    private String motDePasse; // Le nom de la variable Java peut rester en camelCase

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public enum Role {
        ADMINISTRATEUR,
        FOURNISSEUR,
        MAGASINIER
    }

    // --- Getters et Setters (maintenant complets) ---

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}