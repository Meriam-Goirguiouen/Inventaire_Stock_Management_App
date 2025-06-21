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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "mouvement_stock")
@NamedQueries({
    @NamedQuery(name = "MouvementStock.findAll", query = "SELECT m FROM MouvementStock m"),
    @NamedQuery(name = "MouvementStock.findByIdMouvement", query = "SELECT m FROM MouvementStock m WHERE m.idMouvement = :idMouvement"),
    @NamedQuery(name = "MouvementStock.findByDateMouvement", query = "SELECT m FROM MouvementStock m WHERE m.dateMouvement = :dateMouvement"),
    @NamedQuery(name = "MouvementStock.findByTypeMouvement", query = "SELECT m FROM MouvementStock m WHERE m.typeMouvement = :typeMouvement"),
    @NamedQuery(name = "MouvementStock.findByQuantiteModifiee", query = "SELECT m FROM MouvementStock m WHERE m.quantiteModifiee = :quantiteModifiee")})
public class MouvementStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mouvement")
    private Integer idMouvement;
    @Basic(optional = false)
    @Column(name = "date_mouvement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMouvement;
    @Basic(optional = false)
    @Column(name = "type_mouvement")
    private String typeMouvement;
    @Basic(optional = false)
    @Column(name = "quantite_modifiee")
    private int quantiteModifiee;
    @JoinColumn(name = "id_article", referencedColumnName = "id_article")
    @ManyToOne(optional = false)
    private Article idArticle;
    @JoinColumn(name = "id_magasinier", referencedColumnName = "id_magasinier")
    @ManyToOne
    private Magasinier idMagasinier;

    public MouvementStock() {
    }

    public MouvementStock(Integer idMouvement) {
        this.idMouvement = idMouvement;
    }

    public MouvementStock(Integer idMouvement, Date dateMouvement, String typeMouvement, int quantiteModifiee) {
        this.idMouvement = idMouvement;
        this.dateMouvement = dateMouvement;
        this.typeMouvement = typeMouvement;
        this.quantiteModifiee = quantiteModifiee;
    }

    public Integer getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(Integer idMouvement) {
        this.idMouvement = idMouvement;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public int getQuantiteModifiee() {
        return quantiteModifiee;
    }

    public void setQuantiteModifiee(int quantiteModifiee) {
        this.quantiteModifiee = quantiteModifiee;
    }

    public Article getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Article idArticle) {
        this.idArticle = idArticle;
    }

    public Magasinier getIdMagasinier() {
        return idMagasinier;
    }

    public void setIdMagasinier(Magasinier idMagasinier) {
        this.idMagasinier = idMagasinier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMouvement != null ? idMouvement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MouvementStock)) {
            return false;
        }
        MouvementStock other = (MouvementStock) object;
        if ((this.idMouvement == null && other.idMouvement != null) || (this.idMouvement != null && !this.idMouvement.equals(other.idMouvement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.model.MouvementStock[ idMouvement=" + idMouvement + " ]";
    }
    
}
