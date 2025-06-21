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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "administrateur")
@NamedQueries({
    @NamedQuery(name = "Administrateur.findAll", query = "SELECT a FROM Administrateur a"),
    @NamedQuery(name = "Administrateur.findByIdAdministrateur", query = "SELECT a FROM Administrateur a WHERE a.idAdministrateur = :idAdministrateur")})
public class Administrateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_administrateur")
    private Integer idAdministrateur;
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur")
    @OneToOne(optional = false)
    private Utilisateur idUtilisateur;

    public Administrateur() {
    }

    public Administrateur(Integer idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    }

    public Integer getIdAdministrateur() {
        return idAdministrateur;
    }

    public void setIdAdministrateur(Integer idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
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
        hash += (idAdministrateur != null ? idAdministrateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrateur)) {
            return false;
        }
        Administrateur other = (Administrateur) object;
        if ((this.idAdministrateur == null && other.idAdministrateur != null) || (this.idAdministrateur != null && !this.idAdministrateur.equals(other.idAdministrateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.model.Administrateur[ idAdministrateur=" + idAdministrateur + " ]";
    }
    
}
