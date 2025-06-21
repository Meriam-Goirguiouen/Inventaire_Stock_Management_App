/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "commande_fournisseur")
@NamedQueries({
    @NamedQuery(name = "CommandeFournisseur.findAll", query = "SELECT c FROM CommandeFournisseur c"),
    @NamedQuery(name = "CommandeFournisseur.findByIdCommande", query = "SELECT c FROM CommandeFournisseur c WHERE c.idCommande = :idCommande"),
    @NamedQuery(name = "CommandeFournisseur.findByDateCommande", query = "SELECT c FROM CommandeFournisseur c WHERE c.dateCommande = :dateCommande"),
    @NamedQuery(name = "CommandeFournisseur.findByStatut", query = "SELECT c FROM CommandeFournisseur c WHERE c.statut = :statut")})
public class CommandeFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_commande")
    private Integer idCommande;
    @Basic(optional = false)
    @Column(name = "date_commande")
    @Temporal(TemporalType.DATE)
    private Date dateCommande;
    @Basic(optional = false)
    @Column(name = "statut")
    private String statut;
    @JoinColumn(name = "id_fournisseur", referencedColumnName = "id_fournisseur")
    @ManyToOne(optional = false)
    private Fournisseur idFournisseur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCommande")
    private Collection<LigneCommandeFournisseur> ligneCommandeFournisseurCollection;

    public CommandeFournisseur() {
    }

    public CommandeFournisseur(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public CommandeFournisseur(Integer idCommande, Date dateCommande, String statut) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.statut = statut;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Collection<LigneCommandeFournisseur> getLigneCommandeFournisseurCollection() {
        return ligneCommandeFournisseurCollection;
    }

    public void setLigneCommandeFournisseurCollection(Collection<LigneCommandeFournisseur> ligneCommandeFournisseurCollection) {
        this.ligneCommandeFournisseurCollection = ligneCommandeFournisseurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommande != null ? idCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandeFournisseur)) {
            return false;
        }
        CommandeFournisseur other = (CommandeFournisseur) object;
        if ((this.idCommande == null && other.idCommande != null) || (this.idCommande != null && !this.idCommande.equals(other.idCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.model.CommandeFournisseur[ idCommande=" + idCommande + " ]";
    }
    
}
