/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "magasinier")
@NamedQueries({
    @NamedQuery(name = "Magasinier.findAll", query = "SELECT m FROM Magasinier m"),
    @NamedQuery(name = "Magasinier.findByIdMagasinier", query = "SELECT m FROM Magasinier m WHERE m.idMagasinier = :idMagasinier")})
public class Magasinier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_magasinier")
    private Integer idMagasinier;
    @OneToMany(mappedBy = "idMagasinier")
    private Collection<MouvementStock> mouvementStockCollection;
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur")
    @OneToOne(optional = false)
    private Utilisateur idUtilisateur;

    public Magasinier() {
    }

    public Magasinier(Integer idMagasinier) {
        this.idMagasinier = idMagasinier;
    }

    public Integer getIdMagasinier() {
        return idMagasinier;
    }

    public void setIdMagasinier(Integer idMagasinier) {
        this.idMagasinier = idMagasinier;
    }

    public Collection<MouvementStock> getMouvementStockCollection() {
        return mouvementStockCollection;
    }

    public void setMouvementStockCollection(Collection<MouvementStock> mouvementStockCollection) {
        this.mouvementStockCollection = mouvementStockCollection;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMagasinier != null ? idMagasinier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Magasinier)) {
            return false;
        }
        Magasinier other = (Magasinier) object;
        if ((this.idMagasinier == null && other.idMagasinier != null) || (this.idMagasinier != null && !this.idMagasinier.equals(other.idMagasinier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.model.Magasinier[ idMagasinier=" + idMagasinier + " ]";
    }
    
}
