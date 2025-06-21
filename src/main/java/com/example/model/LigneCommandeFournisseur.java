/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "ligne_commande_fournisseur")
@NamedQueries({
    @NamedQuery(name = "LigneCommandeFournisseur.findAll", query = "SELECT l FROM LigneCommandeFournisseur l"),
    @NamedQuery(name = "LigneCommandeFournisseur.findByIdLigneCommande", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.idLigneCommande = :idLigneCommande"),
    @NamedQuery(name = "LigneCommandeFournisseur.findByQuantite", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.quantite = :quantite")})
public class LigneCommandeFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ligne_commande")
    private Integer idLigneCommande;
    @Basic(optional = false)
    @Column(name = "quantite")
    private int quantite;
    @JoinColumn(name = "id_article", referencedColumnName = "id_article")
    @ManyToOne(optional = false)
    private Article idArticle;
    @JoinColumn(name = "id_commande", referencedColumnName = "id_commande")
    @ManyToOne(optional = false)
    private CommandeFournisseur idCommande;

    public LigneCommandeFournisseur() {
    }

    public LigneCommandeFournisseur(Integer idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public LigneCommandeFournisseur(Integer idLigneCommande, int quantite) {
        this.idLigneCommande = idLigneCommande;
        this.quantite = quantite;
    }

    public Integer getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(Integer idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Article getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Article idArticle) {
        this.idArticle = idArticle;
    }

    public CommandeFournisseur getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(CommandeFournisseur idCommande) {
        this.idCommande = idCommande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLigneCommande != null ? idLigneCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneCommandeFournisseur)) {
            return false;
        }
        LigneCommandeFournisseur other = (LigneCommandeFournisseur) object;
        if ((this.idLigneCommande == null && other.idLigneCommande != null) || (this.idLigneCommande != null && !this.idLigneCommande.equals(other.idLigneCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.model.LigneCommandeFournisseur[ idLigneCommande=" + idLigneCommande + " ]";
    }
    
}
