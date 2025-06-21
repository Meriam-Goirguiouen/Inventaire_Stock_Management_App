/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "stock")
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
    @NamedQuery(name = "Stock.findByIdStock", query = "SELECT s FROM Stock s WHERE s.idStock = :idStock"),
    @NamedQuery(name = "Stock.findByQuantiteActuelle", query = "SELECT s FROM Stock s WHERE s.quantiteActuelle = :quantiteActuelle"),
    @NamedQuery(name = "Stock.findBySeuilAlerte", query = "SELECT s FROM Stock s WHERE s.seuilAlerte = :seuilAlerte"),
    @NamedQuery(name = "Stock.findByDerniereMiseAJour", query = "SELECT s FROM Stock s WHERE s.derniereMiseAJour = :derniereMiseAJour")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stock")
    private Integer idStock;
    @Basic(optional = false)
    @Column(name = "quantite_actuelle")
    private int quantiteActuelle;
    @Basic(optional = false)
    @Column(name = "seuil_alerte")
    private int seuilAlerte;
    @Basic(optional = false)
    @Column(name = "derniere_mise_a_jour")
    @Temporal(TemporalType.TIMESTAMP)
    private Date derniereMiseAJour;
    @JoinColumn(name = "id_article", referencedColumnName = "id_article")
    @OneToOne(optional = false)
    private Article idArticle;

    public Stock() {
    }

    public Stock(Integer idStock) {
        this.idStock = idStock;
    }

    public Stock(Integer idStock, int quantiteActuelle, int seuilAlerte, Date derniereMiseAJour) {
        this.idStock = idStock;
        this.quantiteActuelle = quantiteActuelle;
        this.seuilAlerte = seuilAlerte;
        this.derniereMiseAJour = derniereMiseAJour;
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public int getQuantiteActuelle() {
        return quantiteActuelle;
    }

    public void setQuantiteActuelle(int quantiteActuelle) {
        this.quantiteActuelle = quantiteActuelle;
    }

    public int getSeuilAlerte() {
        return seuilAlerte;
    }

    public void setSeuilAlerte(int seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

    public Date getDerniereMiseAJour() {
        return derniereMiseAJour;
    }

    public void setDerniereMiseAJour(Date derniereMiseAJour) {
        this.derniereMiseAJour = derniereMiseAJour;
    }

    public Article getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Article idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStock != null ? idStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.idStock == null && other.idStock != null) || (this.idStock != null && !this.idStock.equals(other.idStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.model.Stock[ idStock=" + idStock + " ]";
    }
    
}
